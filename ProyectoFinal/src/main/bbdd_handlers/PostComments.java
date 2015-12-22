package main.bbdd_handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
			connection.setAutoCommit(false);
			
			PostInfo post = new PostInfo(connection);
			idComment = post.getCommentID(idPost);
			
			insert(idComment, idPost, texto);
			
			connection.commit();
		} catch(SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new ErrorNoLogico(e.getMessage());
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				throw new ErrorNoLogico(e.getMessage());
			}
		}
	}
	
	public void respond(int idPost, String texto, int upLink) throws ErrorNoLogico {
		int idComment;

		if (MyUtil.isNull(texto)) throw new ErrorNoLogico("Texto no puede ser nulo");
		
		try {
			connection.setAutoCommit(false);
			
			PostInfo post = new PostInfo(connection);
			idComment = post.getCommentID(idPost);
			
			insert(idComment, idPost, texto, upLink);
			
			connection.commit();
			connection.setAutoCommit(true);
		} catch(SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new ErrorNoLogico(e.getMessage());
		}
	}
	
	public void removeComment(int idPost, int idComment) throws ErrorNoLogico {
		try {
			connection.setAutoCommit(false);
			
			int resDelete = delete(idPost, idComment);
			
			if (resDelete != 1) {
				connection.rollback();
				throw new ErrorNoLogico("No se ha borrado en POST_COMMENTS con ID_POST = " + idPost + " y ID_COMMENT = " + idComment);
			}
			
			connection.commit();
			connection.setAutoCommit(true);
			
		} catch(SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new ErrorNoLogico(e.getMessage());
		}
	}
	
	public ResultSet getCommentsByPostID(int id) throws ErrorNoLogico {
		
		try {
			String sql = "SELECT ID_COMMENT, TEXTO, FECHA_CREACION, UP_LINK FROM POST_COMMENTS WHERE ID_POST = " + id;
			Statement orden = connection.createStatement();
			ResultSet cursor = orden.executeQuery(sql);
			return cursor;
		} catch(SQLException e) {
			throw new ErrorNoLogico(e.getMessage());
		}
	}
	
	public void insert(int idComment, int idPost, String texto) throws SQLException {
		String sql = "INSERT INTO POST_COMMENTS (ID_COMMENT, ID_POST, TEXTO) "
			   + "VALUES(?, ?, ?)";
		PreparedStatement orden = connection.prepareStatement(sql);
		orden.setInt(1, idComment);
		orden.setInt(2, idPost);
		orden.setString(3, texto);
		
		orden.executeUpdate();
	}
	
	public void insert(int idComment, int idPost, String texto, int upLink) throws SQLException {
		String sql = "INSERT INTO POST_COMMENTS (ID_COMMENT, ID_POST, TEXTO, UP_LINK) "
			   + "VALUES(?, ?, ?, ?)";
		PreparedStatement orden = connection.prepareStatement(sql);
		orden.setInt(1, idComment);
		orden.setInt(2, idPost);
		orden.setString(3, texto);
		orden.setInt(4, upLink);
		
		orden.executeUpdate();
	}
	
	public int delete(int idPost, int idComment) throws SQLException {
		String sql = "DELETE FROM POST_COMMENTS WHERE ID_POST = ? AND ID_COMMENT = ?";
		PreparedStatement orden = connection.prepareStatement(sql);
		orden.setInt(1, idPost);
		orden.setInt(2, idComment);
		
		int resDelete = orden.executeUpdate();
		
		return resDelete;
	}
	
	
	
	
	
	
}
