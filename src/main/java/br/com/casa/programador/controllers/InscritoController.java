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
	
}
