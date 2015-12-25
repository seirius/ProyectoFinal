package main.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.bbdd_handlers.CuentasUsuario;
import main.bbdd_handlers.UsuarioInfo;
import main.bbdd_objects.DatosCuenta;
import main.connection.InitCon;
import main.util.ErrorLogico;
import main.util.ErrorNoLogico;

public class IniciarSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IniciarSesion() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection connection = null;
		InitCon init = new InitCon(getServletContext());
		
		DatosCuenta cuenta = new DatosCuenta(request.getParameter("usuario"), request.getParameter("pass"));
		String page = request.getParameter("page");
		
		try {
			connection = init.getConnection();
			CuentasUsuario cuentasUsuario = new CuentasUsuario(connection);
			if (cuentasUsuario.exists(cuenta)) {
				UsuarioInfo usuarioInfo = new UsuarioInfo(connection);
				String avatarURL = usuarioInfo.selectAvatarURL(cuenta.usuario);
				HttpSession session = request.getSession();
				session.setAttribute("usuario", cuenta.usuario);
				session.setAttribute("avatarURL", avatarURL);
				session.setMaxInactiveInterval(120);
			}
			
			response.sendRedirect(request.getContextPath() + "/jsps/" + page + ".jsp");
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(ErrorLogico e) {
			response.sendRedirect(request.getContextPath() + "/jsps/" + page + ".jsp");
		} catch(ErrorNoLogico e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
