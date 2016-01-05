package main.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;

import main.modelo.CuentasUsuario;
import main.modelo.PostComments;
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
	
	public List<PostInfo> getPosts() throws ErrorNoLogico {
		
		Session session = factory.openSession();
		
		try {
			Criteria criteria = session.createCriteria(PostInfo.class);
			criteria.addOrder(Order.desc("fechaCreacion"));
			
			@SuppressWarnings("unchecked")
			List<PostInfo> posts = criteria.list();
			
			return posts;
		} catch(HibernateException e) {
			throw new ErrorNoLogico(e.getMessage());
		} finally {
			session.close();
		}
	}
	
	public PostInfo getPostComentarios(int idPost) throws ErrorNoLogico {
		Session session = factory.openSession();
		
		try {
			
			PostInfo post = (PostInfo) session.load(PostInfo.class, new Integer(idPost));
			post.getPostCommentses().size();
			
			return post;
		} catch (HibernateException e) {
			throw new ErrorNoLogico(e.getMessage());
		} finally {
			session.close();
		}
	}
	
	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("/main/resources/hibernate.cfg.xml").buildSessionFactory();
		
		try {
			Session session = factory.openSession();
			PostInfo post = (PostInfo) session.load(PostInfo.class, new Integer(5));
			Set<PostComments> setCom = post.getPostCommentses();
			List<PostComments> coms = new ArrayList<PostComments>();
			coms.addAll(setCom);
			System.out.println(coms.get(0).getTexto());
			session.close();
		} catch(HibernateException e) {
			e.printStackTrace();
		} finally {
			if (factory != null) factory.close();
		}
	}
	
}
