package br.com.casa.programador.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.casa.programador.enums.StatusPessoa;
import br.com.casa.programador.models.Publicador;
import br.com.casa.programador.services.PublicadorRepository;

@Controller
@RequestMapping("/teste")
public class TesteController {
	@Autowired
	PublicadorRepository publicadorRepository;
	
	@RequestMapping("/adicionar")
	public String adicionar(Model model) {
		Publicador p = new Publicador();
		p.setNome("Everson Cardoso");
		p.setDatanasc(new Date());
		p.setEmail("evbc@gmail.com");
		p.setnPublicacoes(15);
		p.setSexo("Homem");
		p.setStatus(StatusPessoa.ATIVADO);
		p.setCpf("456456465");
		model.addAttribute("publicador", p);
		publicadorRepository.save(p);
		return "index";
	}
	
	@RequestMapping("/listar")
	public String listPublicadores(Model model) {
		Iterable<Publicador> listaPublicadores;
		listaPublicadores = publicadorRepository.findAll();
		model.addAttribute("publicadores", listaPublicadores);
		return "listar";
	}
}
