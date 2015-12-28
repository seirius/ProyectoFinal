package main.bbdd_objects;

public class Post {

	public int id;
	public String titulo;
	public String texto;
	public String imagenUrl;
	public String etiqueta;
	public long fechaCreacion;
	public int commentContador;
	public String autor;
	
	public Post(int id, String titulo, String texto, String imagenUrl, String etiqueta, long fechaCreacion, int commentContador, String autor) {
		this.id = id;
		this.titulo = titulo;
		this.texto = texto;
		this.imagenUrl = imagenUrl;
		this.etiqueta = etiqueta;
		this.fechaCreacion = fechaCreacion;
		this.commentContador = commentContador;
		this.autor = autor;
	}
}
