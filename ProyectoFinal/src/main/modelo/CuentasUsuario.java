package main.modelo;
// Generated 03-ene-2016 23:30:22 by Hibernate Tools 4.3.1.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CuentasUsuario generated by hbm2java
 */
@Entity
@Table(name = "cuentas_usuario", catalog = "dark_sky")
public class CuentasUsuario implements java.io.Serializable {
	private static final long serialVersionUID = -6496473278429364205L;
	
	
	private String usuario;
	private String pass;
	private Date fechaCreacion;
	private Set<PostInfo> postInfos = new HashSet<PostInfo>(0);
	private Set<PostComments> postCommentses = new HashSet<PostComments>(0);
	private UsuarioInfo usuarioInfo;

	public CuentasUsuario() {
	}
	
	public CuentasUsuario(String usuario, String pass) {
		this.usuario = usuario;
		this.pass = pass;
	}

	public CuentasUsuario(String usuario, String pass, Date fechaCreacion) {
		this.usuario = usuario;
		this.pass = pass;
		this.fechaCreacion = fechaCreacion;
	}

	public CuentasUsuario(String usuario, String pass, Date fechaCreacion, Set<PostInfo> postInfos, Set<PostComments> postCommentses, UsuarioInfo usuarioInfo) {
		this.usuario = usuario;
		this.pass = pass;
		this.fechaCreacion = fechaCreacion;
		this.postInfos = postInfos;
		this.postCommentses = postCommentses;
		this.usuarioInfo = usuarioInfo;
	}

	@Id

	@Column(name = "USUARIO", unique = true, nullable = false, length = 20)
	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Column(name = "PASS", nullable = false, length = 20)
	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_CREACION", nullable = true, length = 19)
	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cuentasUsuario")
	public Set<PostInfo> getPostInfos() {
		return this.postInfos;
	}

	public void setPostInfos(Set<PostInfo> postInfos) {
		this.postInfos = postInfos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cuentasUsuario")
	public Set<PostComments> getPostCommentses() {
		return this.postCommentses;
	}

	public void setPostCommentses(Set<PostComments> postCommentses) {
		this.postCommentses = postCommentses;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "cuentasUsuario")
	public UsuarioInfo getUsuarioInfo() {
		return this.usuarioInfo;
	}

	public void setUsuarioInfo(UsuarioInfo usuarioInfo) {
		this.usuarioInfo = usuarioInfo;
	}

}