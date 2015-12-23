package main.bbdd_handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.bbdd_objects.DatosCuenta;
import main.util.ErrorLogico;
import main.util.ErrorNoLogico;
import main.util.MyUtil;

public class CuentasUsuario {

	private Connection connection;
	
	private final static String USER_NOT_NULL = "Usuario no puede ser nulo";
	private final static String PW_NOT_NULL = "Contraseña no puede ser nula";
	private final static String USER_EXISTS = "Usuario ya existe";
	
	public CuentasUsuario(Connection connection) {
		this.connection = connection;
	}
	
	public void ingresarCuentaUsuario(String usuario, String pass) throws ErrorLogico, ErrorNoLogico {
		
		if (MyUtil.isNull(usuario)) throw new ErrorLogico(USER_NOT_NULL);
		if (MyUtil.isNull(pass)) throw new ErrorLogico(PW_NOT_NULL);
		
		try {
			String sql = "INSERT INTO CUENTAS_USUARIO (USUARIO, PASS) VALUES(?, ?)";
			PreparedStatement orden = connection.prepareStatement(sql);
			orden.setString(1, usuario);
			orden.setString(2, pass);
			orden.executeUpdate();
		} catch(SQLException e) {
			if (e.getErrorCode() == 1062) throw new ErrorLogico(USER_EXISTS);
			throw new ErrorNoLogico(e.getMessage());
		}
	}
	
	public boolean exists(DatosCuenta cuenta) throws ErrorLogico, ErrorNoLogico {
		
		if (MyUtil.isNull(cuenta.usuario)) throw new ErrorLogico(USER_NOT_NULL);
		
		try {
			String sql = "SELECT 1 FROM CUENTAS_USUARIO WHERE USUARIO = ? AND PASS = ?";
			PreparedStatement orden = connection.prepareStatement(sql);
			orden.setString(1, cuenta.usuario);
			orden.setString(2, cuenta.pass);
			ResultSet cursor = orden.executeQuery();
			if (cursor.next()) return true;
			return false;
		} catch(SQLException e) {
			throw new ErrorNoLogico(e.getMessage());
		}
	}
	
	
	
	
	
	
}
