package main.controlador.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import main.controlador.CuentasUsuarioControl;
import main.controlador.UsuarioInfoControl;
import main.modelo.CuentasUsuario;
import main.util.ErrorNoLogico;

public class CrearCuentaUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String DEFAULT_AVATAR_URL = "/img/Raw/Avatar/rawAvatar.png";
       
    public CrearCuentaUsuario() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SessionFactory factory = null;
		
		String usuario = request.getParameter("usuario");
		String pass = request.getParameter("pass");
		String passR = request.getParameter("passR");
		
		if (!pass.equals(passR)) {
			response.sendRedirect(request.getContextPath() + "/jsps/crearCuentaUsuario.jsp");
			return;
		}
		
		try {
			factory = new Configuration().configure("/main/resources/hibernate.cfg.xml").buildSessionFactory();
			
			CuentasUsuarioControl cuentasControl = new CuentasUsuarioControl(factory);
			CuentasUsuario cuenta = cuentasControl.crear(usuario, pass);
			
			UsuarioInfoControl infoControl = new UsuarioInfoControl(factory);
			infoControl.crear(cuenta, DEFAULT_AVATAR_URL);
			
			response.sendRedirect(request.getContextPath() + "/jsps/principal.jsp");
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (ErrorNoLogico e) {
			e.printStackTrace();
		} finally {
			if (factory != null) factory.close();
		}
		
	}

}
