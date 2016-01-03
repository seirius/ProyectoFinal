package main.bbdd_handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.bbdd_objects.Post;
import main.util.ErrorLogico;
import main.util.ErrorNoLogico;
import main.util.MyUtil;

public class PostInfo {

	private Connection connection;
	
	public PostInfo(Connection connection) {
		this.connection = connection;
	}
	
	public int createPost(String titulo, String texto, String imagenURL, String etiqueta, String autor) throws ErrorLogico, ErrorNoLogico {
		int id;
		
		if (MyUtil.isNull(titulo)) throw new ErrorLogico("Titulo no puede ser nulo");
		if (MyUtil.isNull(texto)) throw new ErrorLogico("Texto no puede ser nulo");
		
		try {
			
			connection.setAutoCommit(false);
			
			PostContador postContador = new PostContador(connection);
			id = postContador.getPostContador();
			
			insert(id, titulo, texto, imagenURL, etiqueta, autor);
			
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
		
		return id;
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
			connection.setAutoCommit(true);
		} catch(SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e.printStackTrace();
			}
			throw new ErrorNoLogico(e.getMessage());
		}
	}
	
	public int getCommentID(int id) throws SQLException {
		int idComment = selectCommentContador(id);
		int resUpdate = updateCommentContador(id);

		if (resUpdate != 1) {
			throw new SQLException("No se ha actualizado COMMENT_CONTADOR con ID = " + id);
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
	
	public void insert(int id, String titulo, String texto, String imagenURL, String etiqueta, String autor) throws SQLException {
		String sql = "INSERT INTO POST_INFO (TITULO, TEXTO, IMAGEN_URL, ETIQUETA, AUTOR)"
			+ "VALUES(?, ?, ?, ?, ?, ?)";
		PreparedStatement orden = connection.prepareStatement(sql);
		orden.setInt(1, id);
		orden.setString(2, titulo);
		orden.setString(3, texto);
		orden.setString(4, imagenURL);
		orden.setString(5, etiqueta);
		orden.setString(6, autor);
		
		orden.executeUpdate();
		orden.close();
	}
	
	public int delete(int id) throws SQLException {
		String sql = "DELETE FROM POST_INFO WHERE ID = ?";
		PreparedStatement orden = connection.prepareStatement(sql);
		orden.setInt(1, id);
		return orden.executeUpdate();
	}
	
	public ResultSet getAllPosts() throws ErrorNoLogico {
		try {
			String sql = "SELECT ID, TITULO, TEXTO, FECHA_CREACION FROM POST_INFO ORDER BY FECHA_CREACION DESC";
			Statement orden = connection.createStatement();
			ResultSet cursor = orden.executeQuery(sql);
			return cursor;
		} catch(SQLException e) {
			throw new ErrorNoLogico(e.getMessage());
		}
	}
	
	public Post getSinglePost(int id) throws ErrorNoLogico {
		try {
			String sql = "SELECT TITULO, TEXTO, IMAGEN_URL, ETIQUETA, FECHA_CREACION, COMMENT_CONTADOR, AUTOR FROM POST_INFO WHERE ID = " + id;
			Statement orden = connection.createStatement();
			ResultSet cursor = orden.executeQuery(sql);
			cursor.next();
			
			String titulo = cursor.getString("TITULO");
			String texto = cursor.getString("TEXTO");
			String imagenURL = cursor.getString("IMAGEN_URL");
			String etiqueta = cursor.getString("ETIQUETA");
			long fechaCreacion = cursor.getTimestamp("FECHA_CREACION").getTime();
			int commentContador = cursor.getInt("COMMENT_CONTADOR");
			String autor = cursor.getString("AUTOR");
			
			Post post = new Post(id, titulo, texto, imagenURL, etiqueta, fechaCreacion, commentContador, autor);
			
			return post;
			
		} catch(SQLException e) {
			throw new ErrorNoLogico(e.getMessage());
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
