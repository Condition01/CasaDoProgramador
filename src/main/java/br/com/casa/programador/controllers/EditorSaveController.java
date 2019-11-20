package br.com.casa.programador.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.com.casa.programador.models.Publicacao2;
import br.com.casa.programador.models.users.Publicador;

@RestController
public class EditorSaveController {
	
	Publicador publicador;
	
	@PostMapping("/salvarEdicao")
	public String salvar(@RequestBody String json) {
		Gson gson = new Gson();
		Publicacao2 pub = gson.fromJson(json, Publicacao2.class);
		return "{\"propriedade\": \"ok\"}";
	}
	
}
