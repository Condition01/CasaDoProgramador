package br.com.casa.programador.models.users;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import br.com.casa.programador.models.Tema;

@Entity
@Table(name = "tbl_inscrito")
public class Inscrito extends Pessoa{
	
	@Column(name = "ins_nickname")
	private String nickname;
	
	@ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
        })
	@JoinTable(name = "tbl_inscrito_tema", 
	joinColumns = {@JoinColumn (referencedColumnName = "pes_pessoa")}, 
	inverseJoinColumns = {@JoinColumn(referencedColumnName = "tem_id")})
	private List<Tema> listaTemas = new ArrayList<>();

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public List<Tema> getListaTemas() {
		return listaTemas;
	}

	public void setListaTemas(List<Tema> listaTemas) {
		this.listaTemas = listaTemas;
	}
	
}
