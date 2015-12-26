package main.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.bbdd_handlers.PostInfo;
import main.connection.InitCon;
import main.util.ErrorLogico;
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
		Connection connection = null;
		
		String titulo = request.getParameter("tituloPost");
		String texto = request.getParameter("textoPost");
		
		try {
			InitCon init = new InitCon(getServletContext());
			connection = init.getConnection();
			PostInfo post = new PostInfo(connection);
			int idPost = post.createPost(titulo, texto, null, null);
			request.setAttribute("idPost", new Integer(idPost));
			response.sendRedirect(request.getContextPath() + "/jsps/foro_principal.jsp");
		} catch(SQLException e) {
			//Temporal <- Importante
			e.printStackTrace();
		} catch(ErrorNoLogico e) {
			//Temporal <- Importante
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/jsps/crearPost.jsp");
		} catch(ErrorLogico e) {
			//Temporal <- Importante
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/jsps/crearPost.jsp");
		} finally {
			try {
				if (connection != null) connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
