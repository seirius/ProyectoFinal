package main.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.bbdd_handlers.CuentasUsuario;
import main.connection.InitCon;
import main.util.ErrorLogico;
import main.util.ErrorNoLogico;

public class CrearCuentaUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CrearCuentaUsuario() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection connection = null;
		InitCon init = new InitCon(getServletContext());
		
		String usuario = request.getParameter("usuario");
		String pass = request.getParameter("pass");
		String passR = request.getParameter("passR");
		
		if (!pass.equals(passR)) {
			response.sendRedirect(request.getContextPath() + "/jsps/crearCuentaUsuario.jsp");
			return;
		}
		
		try {
			connection = init.getConnection();
			
			CuentasUsuario cuentasUsuario = new CuentasUsuario(connection);
			cuentasUsuario.ingresarCuentaUsuario(usuario, pass);
			response.sendRedirect(request.getContextPath() + "/jsps/principal.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ErrorNoLogico e) {
			e.printStackTrace();
		} catch (ErrorLogico e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
