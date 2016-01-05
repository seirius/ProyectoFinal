package main.controlador.servlets;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import main.modelo.AvatarImg;
import main.util.ErrorNoLogico;

public class AvatarImgControl {

	private SessionFactory factory;
	
	public AvatarImgControl(SessionFactory factory) {
		this.factory = factory;
	}
	
	public List<AvatarImg> getAvatares() throws ErrorNoLogico {
		Session session = null;
		
		try {
			session = factory.openSession();
			Criteria criteria = session.createCriteria(AvatarImg.class);
			@SuppressWarnings("unchecked")
			List<AvatarImg> avatares = criteria.list();
			return avatares;
		} catch (HibernateException e) {
			throw new ErrorNoLogico(e.getMessage());
		} finally {
			if (session != null) session.close();
		}
	}
}
