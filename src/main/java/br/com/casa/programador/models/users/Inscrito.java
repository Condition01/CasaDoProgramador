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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.casa.programador.interfaces.Observer;
import br.com.casa.programador.models.Tema;

@Entity
@Table(name = "tbl_inscrito")
public class Inscrito extends Pessoa implements Observer{
	
	@NotEmpty(message = "Nickname deve ser preenchido")
	@NotNull(message = "Nickname deve ser preenchido")
	@Size(max = 20, message = "Não deve exceder mais que 20 caracteres!")
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

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
}
