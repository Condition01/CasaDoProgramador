package br.com.casa.programador.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.casa.programador.enums.Sexo;
import br.com.casa.programador.enums.StatusPessoa;
import br.com.casa.programador.enums.TipoPessoa;
import br.com.casa.programador.models.Tema;
import br.com.casa.programador.models.users.Inscrito;
import br.com.casa.programador.models.users.Publicador;
import br.com.casa.programador.repository.InscritoRepository;
import br.com.casa.programador.repository.PublicadorRepository;
import br.com.casa.programador.repository.TemaRepository;

@Controller
@RequestMapping("/teste")
public class TesteController {

	@Autowired
	TemaRepository temaRepository;

	@Autowired
	InscritoRepository inscritoRepository;

	@Autowired
	PublicadorRepository publicadorRepository;

	@RequestMapping("/adicionar")
	public String adicionar(Model model) {
		Publicador p = new Publicador();
		p.setNome("Everson Cardoso");
		p.setDatanasc(new Date());
		p.setEmail("everson@everson.com");
		p.setnPublicacoes(15);
		p.setSenha("123");
		p.setSexo(Sexo.MASCULINO);
		p.setStatus(StatusPessoa.ATIVADO);
		p.settPessoa(TipoPessoa.PUBLICADOR);
		p.setCpf("41846827825");
		p.setSenha("123456");
		p.setConfirmaSenha("123456");
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

	@RequestMapping(value = "/adicionarTema", method = RequestMethod.GET)
	public ModelAndView inserirTemaInscrito() {
		ModelAndView mv = new ModelAndView("mostraInscrito");

		Inscrito insc = new Inscrito();
		insc.setId(3);
		insc.setNome("Everson Cardoso");
		insc.setDatanasc(new Date());
		insc.setEmail("evbc@gmail.com");
		insc.setNickname("Carlão25cm");
		insc.setStatus(StatusPessoa.ATIVADO);
		insc.setCpf("456456465");

		Tema t = new Tema();
		t.setNome("oi");
		t.adicionarInscrito(insc);

		Tema t2 = new Tema();
		t.setNome("oi");
		t2.adicionarInscrito(insc);

		List<Tema> temaLista = new ArrayList<>();
		temaLista.add(t);
		temaLista.add(t2);

		insc.setListaTemas(temaLista);

		inscritoRepository.save(insc);

		mv.addObject("inscrito", insc);

		return mv;

	}

	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/cadastro")
	public String cadastro() {
		return "cadastro";
	}
	
}
