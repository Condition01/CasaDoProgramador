package br.com.casa.programador.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casa.programador.enums.Sexo;
import br.com.casa.programador.models.Tema;
import br.com.casa.programador.models.TrocaSenha;
import br.com.casa.programador.models.users.Inscrito;
import br.com.casa.programador.repository.InscritoRepository;
import br.com.casa.programador.repository.PessoaRepository;
import br.com.casa.programador.repository.TemaRepository;

@Controller
@RequestMapping("/inscrito")
public class InscritoController {

	@Autowired
	Validador validador;

	@Autowired
	InscritoRepository iRepository;

	@Autowired
	PessoaRepository pRepository;

	@Autowired
	TemaRepository tRepository;

	@RequestMapping(value = "/cadastro", method = RequestMethod.GET)
	public String cadastroInscrito(@ModelAttribute Inscrito inscrito, Model model) {
		model.addAttribute("listaSexo", Arrays.asList(Sexo.values()));
		return "cadastro/cadastroInscrito";
	}

	@RequestMapping(value = "/cadastro", method = RequestMethod.POST)
	public String adicionarInscrito(Model model, @ModelAttribute @Valid Inscrito inscrito, BindingResult result,
			@RequestParam("data") String data, Errors errors) {
		if (validador.verificaErros(inscrito, result, data, errors)) {
			return cadastroInscrito(inscrito, model);
		}
		Date date = convertDate(data);
		inscrito.setDatanasc(date);
		iRepository.save(inscrito);
		return mostrarInscrito(inscrito);
	}

	private Date convertDate(String data) {
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(data);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return date;
		}
	}

	public String mostrarInscrito(Inscrito inscrito) {
		return "teste/mostraInscrito";
	}

	@RequestMapping(value = "/acessoInscrito", method = RequestMethod.GET)
	public String acessoInscrito() {
		return "inscrito/telaInscrito";
	}

	@RequestMapping(value = "/inscritoEditar", method = RequestMethod.GET)
	public String inscritoEditar(@ModelAttribute("inscrito") Inscrito inscrito, Model model) {
		if (inscrito.getEmail() == null) {
			Optional<Inscrito> novoInscrito = iRepository.findById(101);
			if (novoInscrito.isPresent()) {
				inscrito = novoInscrito.get();
				model.addAttribute("inscrito", inscrito);
			}
		}
		System.out.println(inscrito.getEmail());
		System.out.println(inscrito.getSexo());
		return "inscrito/inscritoEditar";
	}

	@RequestMapping(value = "/inscritoEditar", method = RequestMethod.POST)
	public String inscritoEditar(@ModelAttribute("inscrito") Inscrito inscrito, BindingResult result, Model model,
			RedirectAttributes mensagem) {
		if (result.hasErrors()) {
			return inscritoEditar(inscrito, model);
		}
		inscrito = inscritoTranspassado(inscrito);
		iRepository.save(inscrito);
		mensagem.addFlashAttribute("mensagem", "Alterações feitas com sucesso");
		return "redirect:/inscrito/inscritoEditar";
	}

	public Inscrito inscritoTranspassado(Inscrito insc) {
		Optional<Inscrito> inscritoAntigo = iRepository.findById(insc.getId());
		Inscrito inscrito;
		if (inscritoAntigo.isPresent()) {
			inscrito = inscritoAntigo.get();
			inscrito.setDatanasc(insc.getDatanasc());
			inscrito.setNome(insc.getNome());
			inscrito.setNickname(insc.getNickname());
			return inscrito;
		} else {
			return null;
		}
	}

	@RequestMapping(value = "/alterarSenha", method = RequestMethod.GET)
	public String alterarSenha(@ModelAttribute("trocaSenha") TrocaSenha trocaSenha, Model model) {
		return "inscrito/alterarSenha";
	}

	@RequestMapping(value = "/alterarSenha", method = RequestMethod.POST)
	public String alterarSenha(@ModelAttribute("trocaSenha") @Valid TrocaSenha trocaSenha, BindingResult result,
			Model model, Errors errors, RedirectAttributes mensagem) {
		if(!validador.alterarSenha(trocaSenha, result, model, errors)) {
			return alterarSenha(trocaSenha, model);
		}
		mensagem.addFlashAttribute("mensagem", "Senha alterada com sucesso!");
		return "redirect:/inscrito/alterarSenha";
	}

	@RequestMapping(value = "/inscritoTema", method = RequestMethod.GET)
	public String inscritoTema(Tema tema, Model model) {
		model.addAttribute("tema", tema);
		Inscrito inscrito = iRepository.findById(101).get();
		List<Tema> listaTema = tRepository.findAllByTema();
		List<Tema> listaTemaUsuario = inscrito.getListaTemas();
		model.addAttribute("listaTema", listaTema);
		model.addAttribute("listaTemaUsuario", listaTemaUsuario);
		return "inscrito/inscritoTema";
	}

	@RequestMapping(value = "/inscritoTema", method = RequestMethod.POST)
	public String inscritoTema(@RequestParam("tema") String tema, Model model, RedirectAttributes mensagem) {
		Tema t = tRepository.findByTema(tema);
		Inscrito inscrito = iRepository.findById(101).get();
		if (!validador.validaAdicaoTema(inscrito, t)) {
			mensagem.addFlashAttribute("mensagemFail", "Não é possivel adicionar um tema ja existente");
			return "redirect:/inscrito/inscritoTema";
		}
		inscrito.adicionarTema(t);
		t.adicionarInscrito(inscrito);
		iRepository.save(inscrito);
		System.out.println("salvo");
		return "redirect:/inscrito/inscritoTema";
	}

	@GetMapping(value = "/removerTema/{id}")
	public String removerTema(@PathVariable("id") int id) {
		Inscrito inscrito = iRepository.findById(101).get();
		inscrito.removerTema(id);
		iRepository.save(inscrito);
		return "redirect:/inscrito/inscritoTema";
	}

	@ModelAttribute(value = "inscrito")
	public Inscrito novoInscrito() {
		return new Inscrito();
	}

	@ModelAttribute(value = "trocaSenha")
	public TrocaSenha trocaSenha() {
		return new TrocaSenha();
	}

}
