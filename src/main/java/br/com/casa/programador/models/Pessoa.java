package br.com.casa.programador.models;
import br.com.casa.programador.enums.StatusPessoa;
import br.com.casa.programador.enums.TipoPessoa;

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

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "tbl_pessoa")
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pes_pessoa")
	private int id;
	
	@Column(name = "pes_nome")
	private String nome;
	
	@Column(name = "pes_datanasc")
	private Date datanasc;
	
	@Column(name = "pes_sexo")
	private String sexo;
	
	@Column(name = "pes_cpf")
	private String cpf;
	
	@Column(name = "pes_email")
	private String email;
	
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

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
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
	
}
