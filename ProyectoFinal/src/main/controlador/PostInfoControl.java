package main.controlador;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import main.modelo.CuentasUsuario;
import main.modelo.PostInfo;
import main.util.ErrorNoLogico;

public class PostInfoControl {

	private SessionFactory factory;
	
	public PostInfoControl(SessionFactory factory) {
		this.factory = factory;
	}
	
	public int crearPost(String titulo, String texto, String imagenUrl, String etiqueta, String autor) throws ErrorNoLogico {
		int id = -1;
		
		Session session = factory.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			PostInfo post = new PostInfo(new CuentasUsuario(autor, autor), titulo, texto, imagenUrl, etiqueta);
			session.save(post);
			id = post.getId();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			throw new ErrorNoLogico(e.getMessage());
		} finally {
			if (session != null) session.close();
		}
		
		return id;
	}
	
	public static void main(String[] args) {
		SessionFactory factory = null;
		try {
			factory = new Configuration().configure("/main/resources/hibernate.cfg.xml").buildSessionFactory();
		} catch(HibernateException e) {
			e.printStackTrace();
			if (factory != null) factory.close();
		}
		PostInfoControl postControl = new PostInfoControl(factory);
		
		try {
			System.out.println(postControl.crearPost("Prueba1", "jaijs HELLO FCKING WORLD", null, null, "Andriy"));
		} catch(ErrorNoLogico e) {
			e.printStackTrace();
		} finally {
			if (factory != null) factory.close();
		}
		
	}
}
