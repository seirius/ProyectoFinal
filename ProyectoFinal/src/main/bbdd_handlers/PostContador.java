package main.bbdd_handlers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostContador {

	private Connection connection;
	
	public PostContador(Connection connection) {
		this.connection = connection;
	}
	
	public int getPostContador() throws SQLException {
		int postContador = select();
		int resUpdate = update();
		if (resUpdate != 1) throw new SQLException("No se ha podido actualizar el contador de la tabla POST_CONTADOR");
		return postContador;
	}
	
	public int select() throws SQLException {
		String sql = "SELECT CONTADOR FROM POST_CONTADOR FOR UPDATE";
		Statement orden = connection.createStatement();
		ResultSet cursor = orden.executeQuery(sql);
		cursor.next();
		int postContador = cursor.getInt("CONTADOR");
		cursor.close();
		return postContador;
	}
	
	public int update() throws SQLException {
		String sql = "UPDATE POST_CONTADOR SET CONTADOR = CONTADOR + 1";
		Statement orden = connection.createStatement();
		int resUpdate = orden.executeUpdate(sql);
		return resUpdate;
	}
	
	
	
	
	
	
	
	
}
