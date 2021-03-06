package main.modelo;
// Generated 03-ene-2016 23:30:22 by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * PostCommentsId generated by hbm2java
 */
@Embeddable
public class PostCommentsId implements java.io.Serializable {
	private static final long serialVersionUID = 7444098622455459881L;
	
	
	private int idComment;
	private int idPost;

	public PostCommentsId() {
	}

	public PostCommentsId(int idComment, int idPost) {
		this.idComment = idComment;
		this.idPost = idPost;
	}

	@Column(name = "ID_COMMENT", nullable = false, precision = 9, scale = 0)
	public int getIdComment() {
		return this.idComment;
	}

	public void setIdComment(int idComment) {
		this.idComment = idComment;
	}

	@Column(name = "ID_POST", nullable = false)
	public int getIdPost() {
		return this.idPost;
	}

	public void setIdPost(int idPost) {
		this.idPost = idPost;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PostCommentsId))
			return false;
		PostCommentsId castOther = (PostCommentsId) other;

		return (this.getIdComment() == castOther.getIdComment()) && (this.getIdPost() == castOther.getIdPost());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdComment();
		result = 37 * result + this.getIdPost();
		return result;
	}

}
