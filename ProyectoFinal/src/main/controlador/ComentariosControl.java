package main.controlador;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import main.modelo.CuentasUsuario;
import main.modelo.PostComments;
import main.modelo.PostCommentsId;
import main.modelo.PostInfo;
import main.util.ErrorNoLogico;

public class ComentariosControl {

	private SessionFactory factory;
	
	public ComentariosControl(SessionFactory factory) {
		this.factory = factory;
	}
	
	public PostComments comentar(int idPost, String texto, int upLink, CuentasUsuario autor) throws ErrorNoLogico {
		Session session = factory.openSession();
		Transaction tx = null;
		PostComments comentario = null;
		
		try {
			tx = session.beginTransaction();
			
			//Recuperar post y su contador de comentarios
			PostInfo post = (PostInfo) session.load(PostInfo.class, new Integer(idPost));
			Integer idComentario = post.getCommentContador();
			if (idComentario == null) idComentario = 0;
			
			//Actualizar el contador
			post.setCommentContador(idComentario + 1);
			session.update(post);
			
			//Guardar comentario
			comentario = new PostComments(new PostCommentsId(idComentario, idPost), autor, post, texto, upLink);
			session.save(comentario);
			tx.commit();
			
			return comentario;
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			throw new ErrorNoLogico(e.getMessage());
		} finally {
			session.close();
		}
	}
}
