package main.bbdd_handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.util.ErrorLogico;
import main.util.ErrorNoLogico;
import main.util.MyUtil;

public class CuentasUsuario {

	private Connection connection;
	
	public CuentasUsuario(Connection connection) {
		this.connection = connection;
	}
	
	public void ingresarCuentaUsuario(String usuario, String pass) throws ErrorLogico, ErrorNoLogico {
		
		if (MyUtil.isNull(usuario)) throw new ErrorLogico("Usuario no puede ser nulo");
		if (MyUtil.isNull(pass)) throw new ErrorLogico("Contraseña no puede ser nula");
		
		try {
			String sql = "INSERT INTO CUENTAS_USUARIO VALUES(?, ?)";
			PreparedStatement orden = connection.prepareStatement(sql);
			orden.setString(1, usuario);
			orden.setString(2, pass);
			orden.executeUpdate();
		} catch(SQLException e) {
			if (e.getErrorCode() == 1062) throw new ErrorLogico("Usuario ya existe");
			throw new ErrorNoLogico(e.getMessage());
		}
	}
}
