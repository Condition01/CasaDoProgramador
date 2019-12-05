package br.com.casa.programador.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	@RequestMapping("/login")
	public String paginaLogin(@RequestParam(value = "error", required = false) String error, Model model) {
		if(error != null) {
			model.addAttribute("error", error);
		}
		return "login/login";
	}
	
}
