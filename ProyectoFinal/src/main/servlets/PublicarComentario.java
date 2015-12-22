package main.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbdd.MySQLConnection;
import main.bbdd_handlers.PostComments;
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
		
		String mysql_url = getServletContext().getInitParameter("mysql_url");
		String usuario = getServletContext().getInitParameter("mysql_usuario");
		String pw = getServletContext().getInitParameter("mysql_pw");
		
		try {
			MySQLConnection msql = new MySQLConnection(mysql_url, usuario, pw);
			connection = msql.getConnection();
			PostComments postComments = new PostComments(connection);
			postComments.comment(idPost, request.getParameter("comentario"));
			response.sendRedirect("jsps/post.jsp?id=" + idPost);
		} catch (SQLException e) {
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
