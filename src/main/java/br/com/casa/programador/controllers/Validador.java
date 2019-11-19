package br.com.casa.programador.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casa.programador.models.Tema;
import br.com.casa.programador.models.TrocaSenha;
import br.com.casa.programador.models.users.Inscrito;
import br.com.casa.programador.models.users.Pessoa;
import br.com.casa.programador.repository.PessoaRepository;

public class Validador {
	@Autowired
	PessoaRepository pRepository;
	
	public boolean verificaErros(Pessoa pessoa, BindingResult result, String data, Errors errors) {
		boolean verificarCadastro = emailJaCadastrado(pessoa.getEmail());
		boolean verificarSenhas = pessoa.getSenha().equals(pessoa.getConfirmaSenha()) ? true : false;
		if (result.hasErrors() || !verificarSenhas || verificarCadastro || data.equals("")) {
			if (!verificarSenhas) {
				errors.rejectValue("confirmaSenha", "senhas.nao.batem", "Senhas não batem!");
			}
			if (verificarCadastro) {
				errors.rejectValue("email", "email.ja.esta.em.uso", "Este email já esta em uso");
			}
			if (data.equals("")) {
				errors.rejectValue("datanasc", "data.invalida", "Por favor informar uma data");
			}
			return true;
		}
		return false;
	}
	
	public boolean emailJaCadastrado(String email) {
		return pRepository.findEmail(email)!=null?true:false;
	}
	
	
	private boolean verificaSenha(TrocaSenha trocaSenha, BindingResult result, Pessoa p, Errors errors) {
		boolean validado = true;
		if(result.hasErrors()) {
			validado = false;
		}
		if(!p.getSenha().equals(trocaSenha.getSenha())) {
			errors.rejectValue("senha", "senha.invalida", "A senha não bate com a senha atual!");
			validado = false;
		}
		if(!trocaSenha.getNovaSenha().equals(trocaSenha.getConfirmaSenha())) {
			errors.rejectValue("confirmaSenha", "senhas.nao.batem", "Senhas não batem!");
			validado = false;
		}
		return validado;
	}
	
	public boolean validaAdicaoTema(Inscrito inscrito, Tema tema) {
		boolean valido = true;
		List<Tema> listaTemas = inscrito.getListaTemas();
		for(Tema t : listaTemas) {
			if(t.getNome().equals(tema.getNome())) {
				valido = false;
			}
		}
		return valido;
		//valido = inscrito.getListaTemas().stream().filter(temaa -> temaa.getId() == tema.getId()).findFirst().isPresent()?false:true;
	}
	
	public boolean alterarSenha(TrocaSenha trocaSenha, 
			BindingResult result, Model model, Errors errors) {
		Pessoa p = pRepository.findById(101).get();
		if(verificaSenha(trocaSenha, result, p, errors)) {
			p.setSenha(trocaSenha.getNovaSenha());
			p.setConfirmaSenha(trocaSenha.getConfirmaSenha());
			pRepository.save(p);
			return true;
		}
		return false;
	}
}
