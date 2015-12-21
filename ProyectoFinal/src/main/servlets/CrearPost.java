package main.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbdd.MySQLConnection;
import main.bbdd_handlers.PostInfo;
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
		
		String mysql_url = getServletContext().getInitParameter("mysql_url");
		String usuario = getServletContext().getInitParameter("mysql_usuario");
		String pw = getServletContext().getInitParameter("mysql_pw");
		
		String titulo = request.getParameter("tituloPost");
		String texto = request.getParameter("postText");
		
		try {
			MySQLConnection msql = new MySQLConnection(mysql_url, usuario, pw);
			connection = msql.getConnection();
			PostInfo post = new PostInfo(connection);
			post.createPost(titulo, texto, null, null);
		} catch(SQLException e) {
			//Temporal <- Importante
			e.printStackTrace();
		} catch(ErrorNoLogico e) {
			//Temporal <- Importante
			e.printStackTrace();
		} catch(ErrorLogico e) {
			//Temporal <- Importante
			e.printStackTrace();
		}
	}

}
