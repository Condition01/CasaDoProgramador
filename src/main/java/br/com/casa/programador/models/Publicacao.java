package br.com.casa.programador.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_publicacao")
public class Publicacao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pub_id")
	private int id;

	@Column(name = "pub_time")
	private String time;

	@Column(name = "pub_version")
	private String version;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "pub", cascade = CascadeType.ALL)
	private List<Blocks> blocks = new ArrayList<>();

	@Column(name = "pub_json")
	private String json;
	
	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public List<Blocks> getBlocks() {
		return blocks;
	}

	public void setBlocks(List<Blocks> blocks) {
		this.blocks = blocks;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
