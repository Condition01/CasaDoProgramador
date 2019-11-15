package br.com.casa.programador.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/editor")
public class EditorController {
	
	@RequestMapping(value = "/editar", method = RequestMethod.GET)
	public String editar() {
		return "paginaEdicao/edicao";
	}
	
}
