package br.com.casa.programador.models.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "tbl_publicador")
public class Publicador extends Pessoa{
	
	@Column(name = "pub_n_publicacoes")
	int nPublicacoes;

	public int getnPublicacoes() {
		return nPublicacoes;
	}

	public void setnPublicacoes(int nPublicacoes) {
		this.nPublicacoes = nPublicacoes;
	}
	
}
