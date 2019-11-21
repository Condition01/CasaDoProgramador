package br.com.casa.programador.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.com.casa.programador.models.Blocks;
import br.com.casa.programador.models.Data;
import br.com.casa.programador.models.Publicacao;
import br.com.casa.programador.models.users.Publicador;
import br.com.casa.programador.repository.BlockRepository;
import br.com.casa.programador.repository.DataRepository;
import br.com.casa.programador.repository.PublicacaoRepository;
import br.com.casa.programador.repository.PublicadorRepository;

@RestController
public class EditorSaveController {
	
	@Autowired
	PublicacaoRepository pubRepository;
	
	@Autowired
	DataRepository dRepository;
	
	@Autowired
	BlockRepository bRepository;
	
	@PostMapping("/salvarEdicao")
	public String salvar(@RequestBody String json) {
		String jsonCopy = json;
		Gson gson = new Gson();
		Publicacao pub = gson.fromJson(json, Publicacao.class);
		for(Blocks b : pub.getBlocks()) {
			b.getData().setBlock(b);
			b.setPub(pub);
		}
//		pub.setJson(jsonCopy);
		pubRepository.save(pub);
		System.out.println("pause");
		return "{\"propriedade\": \"ok\"}";
	}
	
}
