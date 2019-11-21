package br.com.casa.programador.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="tbl_block")
public class Blocks {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="block_id")
	private int id;
	
	@Column(name="block_type")
	private String type;
	
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "block", cascade = CascadeType.ALL)
	private Data data;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="pub_id")
	private Publicacao pub;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Publicacao getPub() {
		return pub;
	}
	public void setPub(Publicacao pub) {
		this.pub = pub;
	}
	
}
