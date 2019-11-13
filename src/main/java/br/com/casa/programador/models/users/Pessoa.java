package br.com.casa.programador.models.users;

import br.com.casa.programador.enums.StatusPessoa;
import br.com.casa.programador.enums.TipoPessoa;
import br.com.casa.programador.models.Sexo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "tbl_pessoa")
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pes_pessoa")
	private int id;

	@NotEmpty(message = "Este campo deve ser preenchido")
	@NotNull(message = "Este campo deve ser preenchido")
	@Size(max = 70, message = "Não deve exceder mais que 70 caracteres!")
	@Column(name = "pes_nome")
	private String nome;

	@Column(name = "pes_datanasc")
	private Date datanasc;

	@Column(name = "pes_sexo")
	@Enumerated(EnumType.STRING)
	private Sexo sexo;

	@CPF
	@NotEmpty(message = "Este campo deve ser preenchido")
	@NotNull(message = "Este campo deve ser preenchido")
	@Column(name = "pes_cpf")
	private String cpf;

	@NotEmpty(message = "Este campo deve ser preenchido")
	@NotNull(message = "Este campo deve ser preenchido")
	@Size(min = 5, max = 30, message = "Não deve exceder mais que 30 caracteres!")
	@Column(name = "pes_email")
	private String email;

	@NotEmpty(message = "Este campo deve ser preenchido")
	@NotNull(message = "Este campo deve ser preenchido")
	@Size(min = 5, max = 20, message = "Senha não deve exceder 20 caracteres")
	@Column(name = "pes_senha")
	private String senha;

	@Enumerated(EnumType.STRING)
	@Column(name = "pes_status")
	private StatusPessoa Status;

	@Enumerated(EnumType.STRING)
	@Column(name = "pes_tipo")
	private TipoPessoa tPessoa;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDatanasc() {
		return datanasc;
	}

	public void setDatanasc(Date datanasc) {
		this.datanasc = datanasc;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public StatusPessoa getStatus() {
		return Status;
	}

	public void setStatus(StatusPessoa status) {
		Status = status;
	}

	public TipoPessoa gettPessoa() {
		return tPessoa;
	}

	public void settPessoa(TipoPessoa tPessoa) {
		this.tPessoa = tPessoa;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
