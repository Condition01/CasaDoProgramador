package br.com.casa.programador.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.casa.programador.enums.Sexo;
import br.com.casa.programador.models.users.Inscrito;
import br.com.casa.programador.models.users.Pessoa;
import br.com.casa.programador.repository.InscritoRepository;
import br.com.casa.programador.repository.PessoaRepository;

@Controller
@RequestMapping("/inscrito")
public class InscritoController {
	
	@Autowired
	Validador validador;
	
	@Autowired
	InscritoRepository iRepository;
	
	@RequestMapping(value = "/cadastro", method = RequestMethod.GET)
	public String cadastroInscrito(@ModelAttribute Inscrito inscrito, Model model) {
		model.addAttribute("inscrito", inscrito);
		model.addAttribute("listaSexo", Arrays.asList(Sexo.values()));
		return "cadastro/cadastroInscrito";
	}

	@RequestMapping(value = "/cadastro", method = RequestMethod.POST)
	public String adicionarInscrito(Model model, @ModelAttribute @Valid Inscrito inscrito, BindingResult result,
			@RequestParam("data") String data, Errors errors) {
		if(validador.verificaErros(inscrito, result, data, errors)) {
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
		if(inscrito.getEmail() == null) {
			Optional<Inscrito> novoInscrito = iRepository.findById(101);
			if(novoInscrito.isPresent()) {
				inscrito=novoInscrito.get();
			}
		}
		System.out.println(inscrito.getEmail());
		System.out.println(inscrito.getSexo());
		model.addAttribute("inscrito", inscrito);
		return "inscrito/inscritoEditar";
	}
	
	@RequestMapping(value = "/inscritoEditar", method = RequestMethod.POST)
	public String inscritoEditar(@ModelAttribute("inscrito") Inscrito inscrito, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return inscritoEditar(inscrito, model);
		}
		inscrito = inscritoTranspassado(inscrito);
		iRepository.save(inscrito);
		return inscritoEditar(inscrito, model);
	}
	
	public Inscrito inscritoTranspassado(Inscrito insc) {
		Optional<Inscrito> inscritoAntigo = iRepository.findById(insc.getId());
		Inscrito inscrito;
		if(inscritoAntigo.isPresent()) {
			inscrito = inscritoAntigo.get();
			inscrito.setDatanasc(insc.getDatanasc());
			inscrito.setNome(insc.getNome());
			inscrito.setNickname(insc.getNickname());
			return inscrito;
		}else {
			return null;
		}
	}
	
	@ModelAttribute(value = "inscrito")
	public Inscrito novoInscrito() {
		return new Inscrito();
	}
	
}
