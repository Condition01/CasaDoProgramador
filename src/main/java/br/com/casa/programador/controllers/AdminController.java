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
import br.com.casa.programador.models.users.Publicador;
import br.com.casa.programador.repository.PessoaRepository;
import br.com.casa.programador.repository.PublicadorRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	Validador validador;
	
	@Autowired
	PublicadorRepository puRepository;
	
	@RequestMapping(value = "/cadastroPublicador", method= RequestMethod.GET)
	public String adicionarPublicador(Publicador publicador, Model model) {
		model.addAttribute("publicador", publicador);
		model.addAttribute("listaSexo", Arrays.asList(Sexo.values()));
		return "cadastro/cadastroPublicador";
	}
	
	@RequestMapping(value = "/cadastroPublicador", method= RequestMethod.POST)
	public String adicionarPublicador(@ModelAttribute @Valid Publicador publicador, 
			BindingResult result, Model model, @RequestParam String data, Errors errors) {
		if(validador.verificaErros(publicador, result, data, errors)) {
			return adicionarPublicador(publicador, model);
		}
		Date date = convertDate(data);
		publicador.setDatanasc(date);
		puRepository.save(publicador);
		return mostrarPublicador(publicador);
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

	public String mostrarPublicador(Publicador publicador) {
		return "teste/mostraPublicador";
	}

		
	@RequestMapping(value = "/acessoAdmin", method= RequestMethod.GET)
	public String telaAdministrador() {
		return "admin/telaAdmin";
	}
}
