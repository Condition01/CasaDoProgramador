package br.com.casa.programador.models;

import java.util.ArrayList;
import java.util.List;

public class Data {
	private String url;
	private String service;
	private String source;
	private String embed;
	private int width;
	private int height;
	private String caption;
	private boolean withBorder;
	private boolean withBackground;
	private boolean stretched;
	private String style;
	private List<String> items = new ArrayList<>();
	private String code;
	private String text;

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

}
