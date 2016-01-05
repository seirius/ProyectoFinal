package main.controlador;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import main.modelo.CuentasUsuario;
import main.modelo.UsuarioInfo;
import main.util.ErrorNoLogico;

public class UsuarioInfoControl {

	private SessionFactory factory;
	
	public UsuarioInfoControl(SessionFactory factory) {
		this.factory = factory;
	}
	
	public void crear(CuentasUsuario usuario, String avatarUrl) throws ErrorNoLogico {
		UsuarioInfo info = new UsuarioInfo(usuario, avatarUrl);
		
		Session session = factory.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.save(info);
			tx.commit();
		} catch(HibernateException e) {
			if (tx != null) tx.rollback();
			throw new ErrorNoLogico(e.getMessage());
		} finally {
			session.close();
		}
	}
	
	public String getAvatarUrl(String usuario) throws ErrorNoLogico {
		String usuarioUrl = "";
		
		Session session = factory.openSession();
		
		try {
			UsuarioInfo info = session.load(UsuarioInfo.class, usuario);
			usuarioUrl = info.getAvatarUrl();
			return usuarioUrl;
		} catch(HibernateException e) {
			throw new ErrorNoLogico(e.getMessage());
		} finally {
			session.close();
		}
	}
}
