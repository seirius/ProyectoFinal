package main.bbdd_handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioInfo {

	private Connection connection;
	
	public UsuarioInfo(Connection connection) {
		this.connection = connection;
	}
	
	public void insertar(String usuario) throws SQLException {
		String sql = "INSERT INTO USUARIO_INFO (USUARIO) VALUES(?)";
		PreparedStatement orden = connection.prepareStatement(sql);
		orden.setString(1, usuario);
		orden.executeUpdate();
	}
	
	public void insertar(String usuario, String avatarURL) throws SQLException {
		String sql = "INSERT INTO USUARIO_INFO (USUARIO, AVATAR_URL) VALUES(?, ?)";
		PreparedStatement orden = connection.prepareStatement(sql);
		orden.setString(1, usuario);
		orden.setString(2, avatarURL);
		orden.executeUpdate();
	}
	
	public void cambiarAvatarURL(String usuario, String avatarURL) throws SQLException {
		String sql = "UPDATE USUARIO_INFO SET AVATAR_URL = ? WHERE USUARIO = ?";
		PreparedStatement orden = connection.prepareStatement(sql);
		orden.setString(1, avatarURL);
		orden.setString(2, usuario);
		int resUpdate = orden.executeUpdate();
		if (resUpdate != 1) throw new SQLException("Update sobre USUARIO_INFO no efectuado");
	}
	
	public String selectAvatarURL(String usuario) throws SQLException {
		String avatarURL = "";
		String sql = "SELECT AVATAR_URL FROM USUARIO_INFO WHERE USUARIO = ?";
		PreparedStatement orden = connection.prepareStatement(sql);
		orden.setString(1, usuario);
		ResultSet cursor = orden.executeQuery();
		if (cursor.next()) avatarURL = cursor.getString("AVATAR_URL");
		return avatarURL;
	}
}
