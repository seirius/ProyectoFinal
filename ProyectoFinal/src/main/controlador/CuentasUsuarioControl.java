package main.controlador;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import main.modelo.CuentasUsuario;
import main.util.ErrorNoLogico;

public class CuentasUsuarioControl {

	private SessionFactory factory;
	
	public CuentasUsuarioControl(SessionFactory factory) {
		this.factory = factory;
	}
	
	public CuentasUsuario crear(String usuario, String pass) throws ErrorNoLogico {
		CuentasUsuario cuenta = new CuentasUsuario(usuario, pass);
		
		Session session = factory.openSession();
		
		try {
			session.save(cuenta);
			return cuenta;
		} catch(HibernateException e) {
			throw new ErrorNoLogico(e.getMessage());
		} finally {
			session.close();
		}
	}
	
	public boolean exists(String usuario) throws ErrorNoLogico {
		boolean exist = false;
		
		Session session = factory.openSession();
		
		try {
			CuentasUsuario cuenta = (CuentasUsuario) session.get(CuentasUsuario.class, usuario);
			
			if (cuenta != null) exist = true;
			return exist;
		} catch (HibernateException e) {
			throw new ErrorNoLogico(e.getMessage());
		} finally {
			session.close();
		}
	}
	
	public boolean verificarCuenta(String usuario, String pass) throws ErrorNoLogico {
		boolean valido = false;
		
		Session session = factory.openSession();
		
		try {
			CuentasUsuario cuenta = (CuentasUsuario) session.get(CuentasUsuario.class, usuario);
			
			if (cuenta != null && cuenta.getPass().equals(pass)) valido = true;
			return valido;
		} catch (HibernateException e) {
			throw new ErrorNoLogico(e.getMessage());
		} finally {
			session.close();
		}
	}
	
	//Solo usar si se sabe que existe
	public CuentasUsuario getCuenta(String usuario) throws ErrorNoLogico {
		CuentasUsuario cuenta = null;
		
		Session session = factory.openSession();
		
		try {
			cuenta = (CuentasUsuario) session.load(CuentasUsuario.class, usuario);
			
			return cuenta;
		} catch (HibernateException e) {
			throw new ErrorNoLogico(e.getMessage());
		} finally {
			session.close();
		}
	}
	
	public static void main(String[] args) {
		SessionFactory factory = null;
		factory = new Configuration().configure("/main/resources/hibernate.cfg.xml").buildSessionFactory();
		
		CuentasUsuarioControl cuentasControl = new CuentasUsuarioControl(factory);
		
		try {
			
			System.out.println(cuentasControl.exists("testss"));
			
		} catch(ErrorNoLogico e) {
			e.printStackTrace();
		} finally {
			if (factory != null) factory.close();
		}
	}
}
