package br.com.casa.programador.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.casa.programador.models.users.Inscrito;
import br.com.casa.programador.models.users.Pessoa;
import br.com.casa.programador.services.InscritoRepository;
import br.com.casa.programador.services.PessoaRepository;

@Controller
@RequestMapping("/acesso")
public class LoginController {
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	@RequestMapping("/login")
	public String paginaLogin() {
		return "login";
	}
	
	@RequestMapping(value = "/logar", method = RequestMethod.POST)
	public String verificarLogin(@RequestParam String email,@RequestParam String senha, HttpSession session) {
		Pessoa p = pessoaRepository.findByEmailAndPassword(email, senha);
		if(p!=null) {
			session.setAttribute("login", p);
			return "listar";
		}
		return "login";
	}
	
	
}
