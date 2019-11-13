package br.com.casa.programador.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/relatorio")
public class RelatorioController {

	@RequestMapping("/telaPrincipal")
	public String paginaRelatorio() {
		return "relatorios/relatorios";
	}
	
	@RequestMapping("/relatorioAssunto")
	public String relatorioAssunto() {
		return "relatorios/relatorioAssunto";
	}
	
	@RequestMapping("/relatorioUsuario")
	public String relatorioInscrito() {
		return "relatorios/relatorioQtdInscritos";
	}
	
	@RequestMapping("/relatorioPublicacao")
	public String relatorioPubliacao() {
		return "relatorios/relatorioPublicacao";
	}
}
