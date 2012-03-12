package org.gtug.vg.news.model;

import java.io.Serializable;

public class Noticia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1460814274272505259L;
	private String titulo;
	private String cuerpo;
	private String autor;
	private String fecha;
	private String url;

	/**
	 * Constructor vacio
	 */
	public Noticia() {
	}

	/**
	 * Constructor con argumentos
	 * 
	 * @param titulo
	 * @param cuerpo
	 * @param autor
	 * @param fecha
	 * @param url
	 */
	public Noticia(String titulo, String cuerpo, String autor, String fecha, String url) {
		super();
		this.titulo = titulo;
		this.cuerpo = cuerpo;
		this.autor = autor;
		this.fecha = fecha;
		this.url = url;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
