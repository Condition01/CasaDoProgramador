package br.com.casa.programador.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.casa.programador.models.Sexo;
import br.com.casa.programador.models.users.Inscrito;

@Controller
@RequestMapping("/inscrito")
public class InscritoController {
	
	@RequestMapping(value = "/cadastro", method = RequestMethod.GET)
	public String cadastroInscrito(@ModelAttribute Inscrito inscrito, Model model) {
		model.addAttribute("inscrito", inscrito);
		model.addAttribute("listaSexo", Arrays.asList(Sexo.values()));
		return "cadastro";
	}

	@RequestMapping(value = "/cadastro", method = RequestMethod.POST) 
	public String adicionarInscrito(Model model,@ModelAttribute @Valid Inscrito inscrito, 
			BindingResult result, @RequestParam("data") String data) {
			Date date = convertDate(data);  
		if(result.hasErrors()) {
			return cadastroInscrito(inscrito, model);
		}
		return "listar";
	}

	private Date convertDate(String data) {
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(data);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			return date;
		}
	}
	
}
