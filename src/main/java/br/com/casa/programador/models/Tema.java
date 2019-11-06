package br.com.casa.programador.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tbl_tema")
public class Tema {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tema_id")
	private int id;
	
	@Column(name = "tema_tema")
	private String tema;
	
	private List<InscritoTema> inscTema;
	private List<PublicacaoTema> pubTema;

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public List<InscritoTema> getInscTema() {
		return inscTema;
	}

	public void setInscTema(List<InscritoTema> inscTema) {
		this.inscTema = inscTema;
	}

	public List<PublicacaoTema> getPubTema() {
		return pubTema;
	}

	public void setPubTema(List<PublicacaoTema> pubTema) {
		this.pubTema = pubTema;
	}

}
