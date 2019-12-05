package br.com.casa.programador.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import br.com.casa.programador.models.Blocks;
import br.com.casa.programador.models.Publicacao;
import br.com.casa.programador.models.Tema;
import br.com.casa.programador.repository.PublicacaoRepository;

@Controller
@RequestMapping("/publicacao")
public class PublicacaoController {

	@Autowired
	PublicacaoRepository pubRepository;

	@RequestMapping(value = "/publicacao", method = RequestMethod.GET)
	public String visualizarPublicacao(Model model) {
		Publicacao publicacao = pubRepository.findById(2).get();
		List<Blocks> conteudo = publicacao.getBlocks();
		for (Blocks c : conteudo) {
			System.out.println(c.getType());
		}
		model.addAttribute("conteudo", conteudo);
		return "publicacao/publicacao";
	}

	@ModelAttribute("publicacao")
	public Publicacao publicacao() {
		return new Publicacao();
	}

	@GetMapping(value = "/vizualisar/{id}")
	public String temoverTema(@PathVariable("id") int id, Model model) {
		Publicacao publicacao = pubRepository.findById(id).get();
		List<Blocks> conteudo = publicacao.getBlocks();
		model.addAttribute("conteudo", conteudo);
		return "publicacao/publicacao";
	}


}
