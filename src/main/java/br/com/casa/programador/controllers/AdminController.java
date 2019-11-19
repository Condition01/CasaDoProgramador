package br.com.casa.programador.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casa.programador.enums.Sexo;
import br.com.casa.programador.models.Tema;
import br.com.casa.programador.models.users.Publicador;
import br.com.casa.programador.repository.PublicadorRepository;
import br.com.casa.programador.repository.TemaRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	Validador validador;
	
	@Autowired
	PublicadorRepository puRepository;
	
	@Autowired
	TemaRepository tRepository;
	
	@RequestMapping(value = "/cadastroPublicador", method= RequestMethod.GET)
	public String adicionarPublicador(Publicador publicador, Model model) {
		model.addAttribute("publicador", publicador);
		model.addAttribute("listaSexo", Arrays.asList(Sexo.values()));
		return "cadastro/cadastroPublicador";
	}
	
	@RequestMapping(value = "/cadastroPublicador", method= RequestMethod.POST)
	public String adicionarPublicador(@ModelAttribute @Valid Publicador publicador, 
			BindingResult result, Model model, @RequestParam String data, Errors errors) {
		if(validador.verificaErros(publicador, result, data, errors)) {
			return adicionarPublicador(publicador, model);
		}
		Date date = convertDate(data);
		publicador.setDatanasc(date);
		puRepository.save(publicador);
		return mostrarPublicador(publicador);
	}
	
	private Date convertDate(String data) {
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			return date;
		}
	}

	public String mostrarPublicador(Publicador publicador) {
		return "teste/mostraPublicador";
	}

		
	@RequestMapping(value = "/acessoAdmin", method= RequestMethod.GET)
	public String telaAdministrador() {
		return "admin/telaAdmin";
	}
	
	@RequestMapping(value = "/criarTema", method= RequestMethod.GET)
	public String telaCriarTema(Model model) {
		
		List<Tema> listaTema = new ArrayList<>();
		listaTema = tRepository.findAll();
		for(Tema t: listaTema) {
			System.out.println(t.getTema()+ " " +t.getId());
		}
		model.addAttribute("listaTema", listaTema);
		
		return "admin/telaCriarTema";
	}
	
	@GetMapping(value = "/removerTema/{id}")
	public String temoverTema(@PathVariable("id") int id) {
		Tema tema = tRepository.findById(id).get();
		tRepository.delete(tema);
		return "redirect:/admin/criarTema";
	}
	
	@RequestMapping(value = "/criarTema", method= RequestMethod.POST)
	public String telaCriarTema(@RequestParam("nomeTema") String nomeTema, Model model, RedirectAttributes mensagem) {
		Tema tema2 = tRepository.findByTema(nomeTema);
		if(nomeTema.equals(tema2.getTema())) {
			mensagem.addFlashAttribute("mensagemFail", "Já existe um tema com este nome");
		} else {
			Tema t = new Tema();
			t.setTema(nomeTema);
			tRepository.save(t);
		}
		return "redirect:/admin/criarTema";
	}

}
