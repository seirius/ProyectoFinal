package main.bbdd_handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.bbdd_objects.Avatar;
import main.util.ErrorLogico;
import main.util.ErrorNoLogico;
import main.util.MyUtil;

public class Avatares {
	
	private Connection connection;
	
	public Avatares(Connection connection) {
		this.connection = connection;
	}
	
	public Avatar getSingleAvatar(String nombreAvatar) throws ErrorLogico, ErrorNoLogico {
		if (MyUtil.isNull(nombreAvatar)) throw new ErrorLogico("Nombre Avatar no puede ser nulo");
		
		try {
			String sql = "SELECT URL_AVATAR FROM AVATAR_IMG WHERE NOMBRE_AVATAR = ?";
			PreparedStatement orden = connection.prepareStatement(sql);
			orden.setString(1, nombreAvatar);
			ResultSet cursor = orden.executeQuery();
			
			if (cursor.next()) {
				String urlAvatar = cursor.getString("URL_AVATAR");
				Avatar avatar = new Avatar(nombreAvatar, urlAvatar);
				return avatar;
			} else {
				throw new ErrorLogico("NO HAY AVATARES CON ESE NOMBRE");
			}
		} catch (SQLException e) {
			throw new ErrorNoLogico(e.getMessage());
		}
	}
	
	public ResultSet getAll() throws ErrorNoLogico {
		try {
			String sql = "SELECT NOMBRE_AVATAR, URL_AVATAR FROM AVATAR_IMG";
			Statement orden = connection.createStatement();
			ResultSet cursor = orden.executeQuery(sql);
			return cursor;
		} catch (SQLException e) {
			throw new ErrorNoLogico(e.getMessage());
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
