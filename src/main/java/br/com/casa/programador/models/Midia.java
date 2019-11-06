package br.com.casa.programador.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_midia")
public class Midia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mid_id")
	private int id;

	@Column(name = "mid_url")
	private String url;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
