package br.com.casa.programador.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_publicacao")
public class Publicacao {
	
	@Id
	@Column(name = "pub_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="pub_titulo")
	private String titulo;
	
	@Column(name="pub_descricao")
	private String descricacao;
	
	@Column(name="pub_texto")
	private String texto;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tem_id", referencedColumnName = "tem_id")
	Tema tema; 
	

	
	
//	private List<Midia> midias;
//
//	private List<PublicacaoTema> publicacaoTema;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricacao() {
		return descricacao;
	}
	public void setDescricacao(String descricacao) {
		this.descricacao = descricacao;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
//	public List<Midia> getMidias() {
//		return midias;
//	}
//	public void setMidia(List<Midia> midias) {
//		this.midias = midias;
//	}
//	public List<PublicacaoTema> getPublicacaoTema() {
//		return publicacaoTema;
//	}
//	public void setPublicacaoTema(List<PublicacaoTema> publicacaoTema) {
//		this.publicacaoTema = publicacaoTema;
//	}
//	public void setMidias(List<Midia> midias) {
//		this.midias = midias;
//	}
	
	
	
}
