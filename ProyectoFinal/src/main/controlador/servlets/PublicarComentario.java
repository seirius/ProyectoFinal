package main.controlador.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import main.controlador.ComentariosControl;
import main.controlador.CuentasUsuarioControl;
import main.modelo.CuentasUsuario;
import main.util.ErrorNoLogico;

public class PublicarComentario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PublicarComentario() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idPost = Integer.parseInt(request.getParameter("idPost"));
		
		SessionFactory factory = null;
		
		String usuario = (String) request.getSession().getAttribute("usuario");
		String texto = request.getParameter("textareaComentarioPost");
		
		try {
			factory = new Configuration().configure("/main/resources/hibernate.cfg.xml").buildSessionFactory();
			
			CuentasUsuarioControl cuentasControl = new CuentasUsuarioControl(factory);
			CuentasUsuario cuenta = cuentasControl.getCuenta(usuario);
			
			ComentariosControl comControl = new ComentariosControl(factory);
			comControl.comentar(idPost, texto, 0, cuenta);
			
			response.sendRedirect("jsps/post.jsp?idPost=" + idPost);
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (ErrorNoLogico e) {
			e.printStackTrace();
		} finally {
			if (factory != null) factory.close();
		}
		
	}

}
