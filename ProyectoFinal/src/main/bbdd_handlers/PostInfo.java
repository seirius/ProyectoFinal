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
			if (MyUtil.isNull(titulo)) throw new ErrorLogico("Titulo no puede ser nulo");
			if (MyUtil.isNull(texto)) throw new ErrorLogico("Texto no puede ser nulo");
			
			PostContador postContador = new PostContador(connection);
			int id = postContador.getPostContador();
			
			insert(id, titulo, texto, imagenURL, etiqueta);
			
		} catch(SQLException e) {
			throw new ErrorNoLogico(e.getMessage());
		}
	}
	
	public void deletePost(int id) throws ErrorNoLogico, ErrorLogico {
		
		try {
			connection.setAutoCommit(false);
			
			int resDelete = delete(id);
			if (resDelete != 1) {
				connection.rollback();
				throw new ErrorLogico("No se ha podido borrar un post con ID = " + id);
			}
			
			connection.commit();
			
		} catch(SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e.printStackTrace();
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
	
	public int getCommentID(int id) throws ErrorNoLogico {
		int idComment = -1;
		int resUpdate;
		
		try {
			connection.setAutoCommit(false);
			
			idComment = selectCommentContador(id);
			resUpdate = updateCommentContador(id);
			
			if (resUpdate != 1) {
				connection.rollback();
				throw new ErrorNoLogico("No se ha actualizado COMMENT_CONTADOR con ID = " + id);
			}
			
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
		
		return idComment;
	}
	
	public int selectCommentContador(int id) throws SQLException {
		int commentContador = -1;
		String sql = "SELECT COMMENT_CONTADOR FROM POST_INFO WHERE ID = ? FOR UPDATE";
		PreparedStatement orden = connection.prepareStatement(sql);
		orden.setInt(1, id);
		ResultSet cursor = orden.executeQuery();
		cursor.next();
		
		commentContador = cursor.getInt("COMMENT_CONTADOR");
		cursor.close();
		
		return commentContador;
	}
	
	public int updateCommentContador(int id) throws SQLException {
		int resUpdate;
		
		String sql = "UPDATE POST_INFO SET COMMENT_CONTADOR = COMMENT_CONTADOR + 1 WHERE ID = ?";
		PreparedStatement orden = connection.prepareStatement(sql);
		orden.setInt(1, id);
		
		resUpdate = orden.executeUpdate();
		
		return resUpdate;
	}
	
	public void insert(int id, String titulo, String texto, String imagenURL, String etiqueta) throws SQLException {
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
	}
	
	public int delete(int id) throws SQLException {
		String sql = "DELETE FROM POST_INFO WHERE ID = ?";
		PreparedStatement orden = connection.prepareStatement(sql);
		orden.setInt(1, id);
		return orden.executeUpdate();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
