package main.bbdd_objects;

import main.util.UtilDates;

public class Comment {

	public int idComment;
	public int idPost;
	public String texto;
	public long fechaCreacion;
	public int upLink;
	
	public Comment(int idComment, int idPost, String texto, long fechaCreacion, int upLink) {
		this.idComment = idComment;
		this.idPost = idPost;
		this.texto = texto;
		this.fechaCreacion = fechaCreacion;
		this.upLink = upLink;
	}
	
	public String getDate() {
		return UtilDates.msToString(fechaCreacion, "dd/MM/yy HH:mm:ss");
	}
}
