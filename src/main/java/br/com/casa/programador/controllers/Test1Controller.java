package br.com.casa.programador.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.casa.programador.enums.TipoPessoa;

@RestController
public class Test1Controller {

	@GetMapping("/test/value")
	public String value() {
		return TipoPessoa.ADMINISTRADOR.toString();
	}
	
}
