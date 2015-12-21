package main.bbdd_handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.util.ErrorLogico;
import main.util.ErrorNoLogico;
import main.util.MyUtil;

public class PostInfo {

	private Connection connection;
	
	public PostInfo(Connection connection) {
		this.connection = connection;
	}
	
	public void createPost(String titulo, String texto, String imagenURL, String etiqueta) throws ErrorLogico, ErrorNoLogico {
		
		try {
			PostContador postContador = new PostContador(connection);
			int id = postContador.getPostContador();
			
			if (MyUtil.isNull(titulo)) throw new ErrorLogico("Titulo no puede ser nulo");
			if (MyUtil.isNull(texto)) throw new ErrorLogico("Texto no puede ser nulo");
			
			String sql = "INSERT INTO POST_INFO (ID, TITULO, TEXTO, IMAGEN_URL, ETIQUETA)"
					   + "VALUES(?, ?, ?, ?, ?)";
			PreparedStatement orden = connection.prepareStatement(sql);
			orden.setInt(1, id);
			orden.setString(2, titulo);
			orden.setString(3, texto);
			orden.setString(4, imagenURL);
			orden.setString(5, etiqueta);
			
			orden.executeUpdate();
			orden.close();
			
		} catch(SQLException e) {
			throw new ErrorNoLogico(e.getMessage());
		}
	}
	
	public void deletePost(int id) throws ErrorNoLogico {
		
		try {
			String sql = "DELETE FROM POST_INFO WHERE ID = ?";
			PreparedStatement orden = connection.prepareStatement(sql);
			orden.setInt(1, id);
			int resDelete = orden.executeUpdate();
			
			if (resDelete != 1) throw new ErrorNoLogico("No se ha podido borrar un post con ID = " + id);
			
		} catch(SQLException e) {
			throw new ErrorNoLogico(e.getMessage());
		}
	}
	
	public int getCommentID(int id) throws ErrorNoLogico {
		int idComment = -1;
		
		try {
			connection.setAutoCommit(false);
			String sql = "SELECT COMMENT_CONTADOR FROM POST_INFO WHERE ID = ? FOR UPDATE";
			PreparedStatement orden = connection.prepareStatement(sql);
			orden.setInt(1, id);
			ResultSet cursor = orden.executeQuery();
			cursor.next();
			
			idComment = cursor.getInt("COMMENT_CONTADOR");
			cursor.close();
			
			sql = "UPDATE POST_INFO SET COMMENT_CONTADOR = COMMENT_CONTADOR + 1 WHERE ID = ?";
			orden = connection.prepareStatement(sql);
			orden.setInt(1, id);
			
			int resUpdate = orden.executeUpdate();
			
			if (resUpdate != 1) throw new ErrorNoLogico("No se ha actualizado COMMENT_CONTADOR con ID = " + id);
			
			connection.commit();
			return idComment;
			
		} catch(SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new ErrorNoLogico(e.getMessage());
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
	
	
	
	
	
	
	
	
	
	
	
	
	
}
