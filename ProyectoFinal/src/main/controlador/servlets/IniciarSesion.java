package main.controlador.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import main.controlador.CuentasUsuarioControl;
import main.controlador.UsuarioInfoControl;
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
		
		SessionFactory factory = null;
		
		String usuario = request.getParameter("usuario");
		String pass = request.getParameter("pass");
		String page = request.getParameter("page");
		
		try {
			factory = new Configuration().configure("/main/resources/hibernate.cfg.xml").buildSessionFactory();
			
			CuentasUsuarioControl cuentasControl = new CuentasUsuarioControl(factory);
			boolean verificar = cuentasControl.verificarCuenta(usuario, pass);
			
			UsuarioInfoControl infoControl = new UsuarioInfoControl(factory);
			
			if (verificar) {
				String avatarURL = infoControl.getAvatarUrl(usuario);
				HttpSession session = request.getSession();
				session.setAttribute("usuario", usuario);
				session.setAttribute("avatarURL", avatarURL);
				session.setMaxInactiveInterval(120);
			}
			
			response.sendRedirect(request.getContextPath() + "/jsps/" + page);
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (ErrorNoLogico e) {
			e.printStackTrace();
		} finally {
			if (factory != null) factory.close();
		}
	}

}
