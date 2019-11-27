package br.com.casa.programador.enums;

public enum StatusPessoa {
	ATIVADO("Ativado"),
	DESATIVO("Desativo"),
	SUSPENSA("Suspensa");
	
	private String descricao;
	
	StatusPessoa(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;	
	}
}
