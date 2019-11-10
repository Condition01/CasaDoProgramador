package br.com.casa.programador.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.casa.programador.models.users.Inscrito;

@Controller
@RequestMapping("/inscrito")
public class InscritoController {
	
	@RequestMapping(value = "/cadastro", method = RequestMethod.GET)
	public ModelAndView cadastroInscrito(Inscrito inscrito) {
		ModelAndView mv = new ModelAndView("cadastro");
		mv.addObject("inscrito", inscrito);
		return mv;
	}

	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST) 
	public String adicionarInscrito(Model model, @Valid Inscrito inscrito, BindingResult bResult, @RequestParam("data") String data) {
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(data);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		if(bResult.hasErrors()) {
			model.addAttribute("inscrito", inscrito);
			return "cadastro";
		}
		return "listar";
	}
	
}
