package main.controlador;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import main.modelo.CuentasUsuario;
import main.modelo.PostInfo;
import main.util.ErrorNoLogico;

public class PostInfoControl {

	private SessionFactory factory;
	
	public PostInfoControl(SessionFactory factory) {
		this.factory = factory;
	}
	
	public PostInfo crearPost(String titulo, String texto, String imagenUrl, String etiqueta, CuentasUsuario autor) throws ErrorNoLogico {
		
		Session session = factory.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			PostInfo post = new PostInfo(autor, titulo, texto, imagenUrl, etiqueta);
			session.save(post);
			tx.commit();
			return post;
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			throw new ErrorNoLogico(e.getMessage());
		} finally {
			if (session != null) session.close();
		}
		
	}
	
	public PostInfo getPost(int idPost) throws ErrorNoLogico {
		PostInfo post = null;
		
		Session session = factory.openSession();
		
		try {
			post = (PostInfo) session.load(PostInfo.class, new Integer(idPost));
			
			return post;
		} catch (HibernateException e) {
			throw new ErrorNoLogico(e.getMessage());
		} finally {
			session.close();
		}
	}
	
//	public static void main(String[] args) {
//		SessionFactory factory = null;
//		try {
//			factory = new Configuration().configure("/main/resources/hibernate.cfg.xml").buildSessionFactory();
//		} catch(HibernateException e) {
//			e.printStackTrace();
//			if (factory != null) factory.close();
//		}
//		PostInfoControl postControl = new PostInfoControl(factory);
//		
//		try {
//			System.out.println(postControl.crearPost("Prueba1", "jaijs HELLO FCKING WORLD", null, null, "Andriy"));
//		} catch(ErrorNoLogico e) {
//			e.printStackTrace();
//		} finally {
//			if (factory != null) factory.close();
//		}
//		
//	}
}
