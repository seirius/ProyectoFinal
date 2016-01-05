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
import main.controlador.PostInfoControl;
import main.modelo.CuentasUsuario;
import main.modelo.PostInfo;
import main.util.ErrorNoLogico;

public class CrearPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CrearPost() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessionFactory factory = null;
		
		String titulo = request.getParameter("tituloPost");
		String texto = request.getParameter("textoPost");
		String usuario = (String) request.getSession().getAttribute("usuario");
		
		try {
			factory = new Configuration().configure("/main/resources/hibernate.cfg.xml").buildSessionFactory();
			
			CuentasUsuarioControl cuentasControl = new CuentasUsuarioControl(factory);
			CuentasUsuario autor = cuentasControl.getCuenta(usuario);
			
			PostInfoControl postControl = new PostInfoControl(factory);
			PostInfo post = postControl.crearPost(titulo, texto, null, null, autor);
			int idPost = post.getId();
			request.setAttribute("idPost", new Integer(idPost));
			response.sendRedirect(request.getContextPath() + "/jsps/foro_principal.jsp");
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (ErrorNoLogico e) {
			e.printStackTrace();
		} finally {
			if (factory != null) factory.close();
		}
	}

}
