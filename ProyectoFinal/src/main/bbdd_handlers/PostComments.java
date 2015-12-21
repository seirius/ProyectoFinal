package main.bbdd_handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.util.ErrorNoLogico;
import main.util.MyUtil;

public class PostComments {

	private Connection connection;
	
	public PostComments(Connection connection) {
		this.connection = connection;
	}
	
	public void comment(int idPost, String texto) throws ErrorNoLogico {
		int idComment;
		
		if (MyUtil.isNull(texto)) throw new ErrorNoLogico("Texto no puede ser nulo");
		
		try {
			PostInfo post = new PostInfo(connection);
			idComment = post.getCommentID(idPost);
			System.out.println(idComment);
			
			String sql = "INSERT INTO POST_COMMENTS (ID_COMMENT, ID_POST, TEXTO) "
					   + "VALUES(?, ?, ?)";
			PreparedStatement orden = connection.prepareStatement(sql);
			orden.setInt(1, idComment);
			orden.setInt(2, idPost);
			orden.setString(3, texto);
			
			int resInsert = orden.executeUpdate();
			
			if (resInsert != 1) throw new ErrorNoLogico("No se ha insertado en POST_COMMENTS con ID_POST = " + idPost + " y ID_COMMENT = " + idComment);
			
		} catch(SQLException e) {
			throw new ErrorNoLogico(e.getMessage());
		}
	}
	
	public void respond(int idPost, String texto, int upLink) throws ErrorNoLogico {
		int idComment;

		if (MyUtil.isNull(texto)) throw new ErrorNoLogico("Texto no puede ser nulo");
		
		try {
			PostInfo post = new PostInfo(connection);
			idComment = post.getCommentID(idPost);
			
			String sql = "INSERT INTO POST_COMMENTS (ID_COMMENT, ID_POST, TEXTO, UP_LINK) "
					   + "VALUES(?, ?, ?, ?)";
			PreparedStatement orden = connection.prepareStatement(sql);
			orden.setInt(1, idComment);
			orden.setInt(2, idPost);
			orden.setString(3, texto);
			orden.setInt(4, upLink);
			
			int resInsert = orden.executeUpdate();
			
			if (resInsert != 1) throw new ErrorNoLogico("No se ha insertado en POST_COMMENTS con ID_POST = " + idPost + " y ID_COMMENT = " + idComment);
			
		} catch(SQLException e) {
			throw new ErrorNoLogico(e.getMessage());
		}
	}
	
	public void removeComment(int idPost, int idComment) throws ErrorNoLogico {
		try {
			String sql = "DELETE FROM POST_COMMENTS WHERE ID_POST = ? AND ID_COMMENT = ?";
			PreparedStatement orden = connection.prepareStatement(sql);
			orden.setInt(1, idPost);
			orden.setInt(2, idComment);
			
			int resDelete = orden.executeUpdate();
			
			if (resDelete != 1) throw new ErrorNoLogico("No se ha borrado en POST_COMMENTS con ID_POST = " + idPost + " y ID_COMMENT = " + idComment);
			
		} catch(SQLException e) {
			throw new ErrorNoLogico(e.getMessage());
		}
	}
}
