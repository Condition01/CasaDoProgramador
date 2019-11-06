package br.com.casa.programador.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_insc_tema")
public class InscritoTema {
	
	private Inscrito inscrito;
	private Tema tema;
	
	public Inscrito getInscrito() {
		return inscrito;
	}
	public void setInscrito(Inscrito inscrito) {
		this.inscrito = inscrito;
	}
	public Tema getTema() {
		return tema;
	}
	public void setTema(Tema tema) {
		this.tema = tema;
	}
	
	
}
