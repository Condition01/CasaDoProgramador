package br.com.casa.programador.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.casa.programador.models.Publicacao;
import br.com.casa.programador.repository.PublicacaoRepository;

@Controller
public class HomeController {

	@Autowired
	PublicacaoRepository pubRepository;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model) {
		List<Publicacao> listaPub = pubRepository.findAll();
		model.addAttribute("listaPub", listaPub);
		
		return "home";
	}
	
}
