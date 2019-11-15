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
public class Tema{
	
	@Id
	@Column(name = "tem_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "tem_tema")
	private String tema;
	
	@ManyToMany
	@JoinTable(name = "tbl_inscrito_tema", 
	joinColumns = {@JoinColumn (referencedColumnName = "tem_id")}, 
	inverseJoinColumns = {@JoinColumn(referencedColumnName = "pes_pessoa")})
	private List<Inscrito> listaInscrito = new ArrayList<>();
//	private List<PublicacaoTema> pubTema;
	
	@OneToOne(mappedBy = "tema")
	Publicacao pub;

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}
	
	public void adicionarInscrito(Inscrito insc){
		this.listaInscrito.add(insc);
	}

}
