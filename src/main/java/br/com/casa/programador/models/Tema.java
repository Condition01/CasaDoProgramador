package br.com.casa.programador.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

	@Column(name = "tem_nome")
	private String nome;
	
	@ManyToMany(mappedBy = "listaTemas", cascade = {CascadeType.ALL})
	private List<Inscrito> listaInscrito = new ArrayList<>();
//	private List<PublicacaoTema> pubTema;

	@OneToOne(mappedBy = "tema", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Publicacao publicacao;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public Publicacao getPublicacao() {
		return publicacao;
	}

	public void setPublicacao(Publicacao publicacao) {
		this.publicacao = publicacao;
	}
	
}
