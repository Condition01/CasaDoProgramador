package br.com.casa.programador.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_publicador_tema")
public class PublicacaoTema {

	private Publicador publicador;
	private Tema tema;
	
	public Publicador getPublicador() {
		return publicador;
	}
	public void setPublicador(Publicador publicador) {
		this.publicador = publicador;
	}
	public Tema getTema() {
		return tema;
	}
	public void setTema(Tema tema) {
		this.tema = tema;
	}
	
}
