package main.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.bbdd_handlers.PostComments;
import main.connection.InitCon;
import main.util.ErrorLogico;
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
		
		Connection connection = null;
		
		InitCon init = new InitCon(getServletContext());
		String usuario = (String) request.getSession().getAttribute("usuario");
		
		try {
			connection = init.getConnection();
			PostComments postComments = new PostComments(connection);
			postComments.comment(idPost, request.getParameter("textareaComentarioPost"), usuario);
			response.sendRedirect("jsps/post.jsp?idPost=" + idPost);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ErrorLogico e) {
			e.printStackTrace();
		} catch (ErrorNoLogico e) {
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
