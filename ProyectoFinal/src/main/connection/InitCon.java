package main.connection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;

import bbdd.MySQLConnection;

public class InitCon {

	private ServletContext context;
	
	public InitCon(ServletContext context) {
		this.context = context;
	}
	
	public Connection getConnection() throws SQLException {
		Connection connection = null;
		String mysql_url = context.getInitParameter("mysql_url");
		String mysql_usuario = context.getInitParameter("mysql_usuario");
		String mysql_pw = context.getInitParameter("mysql_pw");
		
		MySQLConnection msql = new MySQLConnection(mysql_url, mysql_usuario, mysql_pw);
		connection = msql.getConnection();
		
		return connection;
	}
}
