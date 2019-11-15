package br.com.casa.programador.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

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
	PessoaRepository pRepository;
	
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
		Date date = convertDate(data);
		inscrito.setDatanasc(date);
		boolean verificarCadastro = emailJaCadastrado(inscrito.getEmail());
		boolean verificarSenhas = inscrito.getSenha().equals(inscrito.getConfirmaSenha()) ? true : false;
		if (result.hasErrors() || !verificarSenhas || verificarCadastro) {
			if (!verificarSenhas) {
				errors.rejectValue("confirmaSenha", "senhas.nao.batem", "Senhas não batem!");
			}
			if (verificarCadastro) {
				errors.rejectValue("email", "email.ja.esta.em.uso", "Este email já esta em uso");
			}
			return cadastroInscrito(inscrito, model);
		}
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

	public boolean emailJaCadastrado(String email) {
		return pRepository.findEmail(email)!=null?true:false;
	}
	
	public String mostrarInscrito(Inscrito inscrito) {
		return "mostraInscrito";
	}
}
