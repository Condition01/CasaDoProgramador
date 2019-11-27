package br.com.casa.programador.enums;

public enum TipoPessoa {
	PUBLICADOR("Publicador"),
	ADMINISTRADOR("Administrador"),
	USUARIO("Usuario");
	
	private String descricao;
	
	TipoPessoa(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;	
	}
}
