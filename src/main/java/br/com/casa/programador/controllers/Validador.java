package br.com.casa.programador.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

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
}
