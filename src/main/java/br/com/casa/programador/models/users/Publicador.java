package br.com.casa.programador.models.users;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.casa.programador.models.Publicacao;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "tbl_publicador")
public class Publicador extends Pessoa{
	
	@Column(name = "pub_n_publicacoes", nullable = true)
	int nPublicacoes;
	
	@OneToOne(mappedBy = "publicador", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Publicacao publicacao;

	public int getnPublicacoes() {
		return nPublicacoes;
	}
	
	public Publicacao getPublicacao() {
		return publicacao;
	}

	public void setPublicacao(Publicacao publicacao) {
		this.publicacao = publicacao;
	}

	public void setnPublicacoes(int nPublicacoes) {
		this.nPublicacoes = nPublicacoes;
	}
	
}
