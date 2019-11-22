package br.com.casa.programador.controllers;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casa.programador.models.TrocaSenha;
import br.com.casa.programador.models.users.Inscrito;
import br.com.casa.programador.models.users.Publicador;
import br.com.casa.programador.repository.PublicadorRepository;

@Controller
@RequestMapping("/publicador")
public class PublicadorController {

	@Autowired
	Validador validador;

	@Autowired
	PublicadorRepository pRepository;

	@RequestMapping(value = "/acessoPublicador", method = RequestMethod.GET)
	public String acessoPublicador(Publicador publicador, Model model) {
		publicador = pRepository.findById(1).get();
		model.addAttribute("publicador", publicador);
		return "publicador/telaPublicador";
	}

	@RequestMapping(value = "/alterarSenha", method = RequestMethod.GET)
	public String alterarSenha(TrocaSenha trocaSenha, Model model) {
		return "publicador/alterarSenha";
	}

	@RequestMapping(value = "/alterarSenha", method = RequestMethod.POST)
	public String alterarSenha(@ModelAttribute @Valid TrocaSenha trocaSenha, BindingResult result, Model model,
			Errors errors, RedirectAttributes mensagem) {
		if (!validador.alterarSenha(trocaSenha, result, model, errors)) {
			return alterarSenha(trocaSenha, model);
		}
		mensagem.addFlashAttribute("mensagem", "Senha alterada com sucesso!");
		return "redirect:/publicador/alterarSenha";
	}

	@RequestMapping(value = "/publicadorEditar", method = RequestMethod.GET)
	public String publicadorEditar(@ModelAttribute("publicador") Publicador publicador, Model model) {
		if (publicador.getEmail() == null) {
			Optional<Publicador> novoPublicador = pRepository.findById(103);
			if (novoPublicador.isPresent()) {
				publicador = novoPublicador.get();
				model.addAttribute("publicador", publicador);
			}
		}
		return "publicador/publicadorEditar";
	}

	
	@RequestMapping(value = "/publicadorEditar", method = RequestMethod.POST)
	public String inscritoEditar(@ModelAttribute("inscrito") Publicador publicador, BindingResult result, Model model,
			RedirectAttributes mensagem) {
		if (result.hasErrors()) {
			return publicadorEditar(publicador, model);
		}
		publicador = publicadorTranspassado(publicador);
		pRepository.save(publicador);
		mensagem.addFlashAttribute("mensagem", "Alterações feitas com sucesso");
		return "redirect:/publicador/publicadorEditar";
	}

	public Publicador publicadorTranspassado(Publicador pub) {
		Optional<Publicador> publicadorAntigo = pRepository.findById(pub.getId());
		Publicador publicador;
		if (publicadorAntigo.isPresent()) {
			publicador = publicadorAntigo.get();
			publicador.setDatanasc(pub.getDatanasc());
			publicador.setNome(pub.getNome());
			return publicador;
		} else {
			return null;
		}
	}
	
	@ModelAttribute("publicador")
	public Publicador novoPublicador() {
		return new Publicador();
	}

}
