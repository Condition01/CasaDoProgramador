package br.com.casa.programador.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.casa.programador.models.Blocks;
import br.com.casa.programador.models.Publicacao;
import br.com.casa.programador.repository.PublicacaoRepository;

@Controller
@RequestMapping("/publicacao")
public class PublicacaoController {

	@Autowired
	PublicacaoRepository pubRepository;

	@RequestMapping(value = "/publicacao", method = RequestMethod.GET)
	public String visualizarPublicacao(Model model) {
		Publicacao publicacao = pubRepository.findById(1).get();
		List<Blocks> conteudo = publicacao.getBlocks();
		for(Blocks c : conteudo) {
			System.out.println(c.getType());
		}
		model.addAttribute("conteudo", conteudo);
		return "publicacao/publicacao";
	}

	@ModelAttribute("publicacao")
	public Publicacao publicacao() {
		return new Publicacao();
	}

}
