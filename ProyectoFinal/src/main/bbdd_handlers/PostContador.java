package main.bbdd_handlers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.util.ErrorNoLogico;

public class PostContador {

	private Connection connection;
	
	public PostContador(Connection connection) {
		this.connection = connection;
	}
	
	public int getPostContador() throws ErrorNoLogico {
		int postContador = -1;
		
		try {
			connection.setAutoCommit(false);
			//Seleccionar contador
			String sql = "SELECT CONTADOR FROM POST_CONTADOR FOR UPDATE";
			Statement orden = connection.createStatement();
			ResultSet cursor = orden.executeQuery(sql);
			cursor.next();
			postContador = cursor.getInt("CONTADOR");
			cursor.close();
			
			//Update contador
			sql = "UPDATE POST_CONTADOR SET CONTADOR = CONTADOR + 1";
			int resUpdate = orden.executeUpdate(sql);
			
			if (resUpdate != 1) throw new ErrorNoLogico("No se ha podido actualizar el contador de la tabla POST_CONTADOR");
			
			connection.commit();
		} catch(SQLException e) {
			try {
				connection.rollback();
				connection.setAutoCommit(true);
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
		
		return postContador;
	}
}
