package br.com.casa.programador.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_inscrito")
public class Inscrito extends Pessoa{
	
	@Column(name = "insc_nickname")
	String nickname;
	
	@Column(name = "inscritoTema")
	List<InscritoTema> inscritoTema;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public List<InscritoTema> getInscritoTema() {
		return inscritoTema;
	}

	public void setInscritoTema(List<InscritoTema> inscritoTema) {
		this.inscritoTema = inscritoTema;
	}
	
}
