package main.noticias_portada;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import main.util.ErrorNoLogico;

public class NoticiasPortada {

	private Connection connection;
	
	public NoticiasPortada(Connection connection) {
		this.connection = connection;
	}
	
	public ArrayList<Noticia> getNoticias() throws ErrorNoLogico {
		
		ArrayList<Noticia> noticias = new ArrayList<Noticia>();
		
		try {
			String sql = "SELECT IMAGEN_URL, TITULO, TEXTO FROM NOTICIAS_PORTADA";
			Statement orden = connection.createStatement();
			ResultSet cursor = orden.executeQuery(sql);
			boolean hay = cursor.next();
			while(hay) {
				Noticia noticia = new Noticia(cursor.getString("IMAGEN_URL"), cursor.getString("TITULO"), cursor.getString("TEXTO"));
				noticias.add(noticia);
				hay = cursor.next();
			}
		} catch(SQLException e) {
			throw new ErrorNoLogico(e.getMessage());
		}
		
		return noticias;
	}
}
