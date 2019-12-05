package br.com.casa.programador.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="tbl_data")
public class Data {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="data_id")
	private int id;
	
	@Column(name="data_url")
	private String url;
	
	@Column(name="data_service")
	private String service;
	
	@Column(name="data_source")
	private String source;
	
	@Column(name="data_embed")
	private String embed;
	
	@Column(name="data_width")
	private int width;
	
	@Column(name="data_height")
	private int height;
	
	@Column(name="data_caption")
	private String caption;
	
	@Column(name="data_withBorder")
	private boolean withBorder;
	
	@Column(name="data_withBackground")
	private boolean withBackground;
	
	@Column(name="data_stretched")
	private boolean stretched;
	
	@Column(name="data_style")
	private String style;
	
	@Column(name="data_code", length = 500)
	private String code;
	
	@Column(name="data_text", length = 800)
	private String text;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="block_id")
	private Blocks block;
	
	@ElementCollection
	@CollectionTable(name = "tbl_items")
	private List<String> items = new ArrayList<>();
	
	@Column(name="data_level")
	private int level;
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public boolean isWithBorder() {
		return withBorder;
	}

	public void setWithBorder(boolean withBorder) {
		this.withBorder = withBorder;
	}

	public boolean isWithBackground() {
		return withBackground;
	}

	public void setWithBackground(boolean withBackground) {
		this.withBackground = withBackground;
	}

	public boolean isStretched() {
		return stretched;
	}

	public void setStretched(boolean stretched) {
		this.stretched = stretched;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public List<String> getItems() {
		return items;
	}

	public void setItems(List<String> items) {
		this.items = items;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getEmbed() {
		return embed;
	}

	public void setEmbed(String embed) {
		this.embed = embed;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Blocks getBlock() {
		return block;
	}

	public void setBlock(Blocks block) {
		this.block = block;
	}

}
