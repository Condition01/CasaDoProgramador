package br.com.casa.programador.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.casa.programador.models.Publicacao;
import br.com.casa.programador.models.Tema;
import br.com.casa.programador.repository.PublicacaoRepository;
import br.com.casa.programador.repository.TemaRepository;

@Controller
@RequestMapping("/editor")
public class EditorController {
	
	@Autowired
	TemaRepository tRepository;

	@RequestMapping(value = "/editar", method = RequestMethod.GET)
	public String editar(Model model) {
		List<Tema> listaTema = tRepository.findAll();
		model.addAttribute("listaTema", listaTema);
		return "paginaEdicao/edicao";
	}
	
	@RequestMapping(value = "/editar", method = RequestMethod.POST)
	public String editar(@RequestBody Publicacao publicacao) {
		return "paginaEdicao/edicao";
	}

}
