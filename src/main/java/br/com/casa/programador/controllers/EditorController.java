package br.com.casa.programador.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.casa.programador.models.Publicacao;

@Controller
@RequestMapping("/editor")
public class EditorController {
	
	@RequestMapping(value = "/editar", method = RequestMethod.GET)
	public String editar() {
		return "paginaEdicao/edicao";
	}
	
	@RequestMapping(value = "/editar", method = RequestMethod.POST)
	public String editar(@RequestBody Publicacao publicacao) {
		return "paginaEdicao/edicao";
	}
	
}
