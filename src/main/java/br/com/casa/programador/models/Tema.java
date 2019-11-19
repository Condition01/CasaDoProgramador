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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.casa.programador.models.users.Inscrito;

@Entity
@Table(name = "tbl_tema")
public class Tema {

	@Id
	@Column(name = "tem_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "tem_tema")
	private String tema;


	@ManyToMany(mappedBy = "listaTemas", cascade = {CascadeType.ALL})
	private List<Inscrito> listaInscrito = new ArrayList<>();
//	private List<PublicacaoTema> pubTema;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public void adicionarInscrito(Inscrito insc) {
		this.listaInscrito.add(insc);
	}
	
	public void removerInscrito(Inscrito insc) {
		this.listaInscrito.remove(insc);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Inscrito> getListaInscrito() {
		return listaInscrito;
	}

	public void setListaInscrito(List<Inscrito> listaInscrito) {
		this.listaInscrito = listaInscrito;
	}

	public Publicacao getPub() {
		return pub;
	}

	public void setPub(Publicacao pub) {
		this.pub = pub;
	}

	
	
}
