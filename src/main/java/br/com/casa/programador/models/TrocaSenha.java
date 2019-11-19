package br.com.casa.programador.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class TrocaSenha {
	
	@NotEmpty(message = "Senha não pode estar vazia!")
	private String senha;
	
	private String novaSenha;
	@Size(min = 6, max = 20 ,message = "Senha deve conter entre 6 e 20 caracteres")
	private String confirmaSenha;
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNovaSenha() {
		return novaSenha;
	}
	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}
	public String getConfirmaSenha() {
		return confirmaSenha;
	}
	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}
}
