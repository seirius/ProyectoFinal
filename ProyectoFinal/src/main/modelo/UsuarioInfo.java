package main.modelo;
// Generated 03-ene-2016 23:30:22 by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * UsuarioInfo generated by hbm2java
 */
@Entity
@Table(name = "usuario_info", catalog = "dark_sky")
public class UsuarioInfo implements java.io.Serializable {

	private String usuario;
	private CuentasUsuario cuentasUsuario;
	private String avatarUrl;

	public UsuarioInfo() {
	}

	public UsuarioInfo(CuentasUsuario cuentasUsuario) {
		this.cuentasUsuario = cuentasUsuario;
	}

	public UsuarioInfo(CuentasUsuario cuentasUsuario, String avatarUrl) {
		this.cuentasUsuario = cuentasUsuario;
		this.avatarUrl = avatarUrl;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "cuentasUsuario") )
	@Id
	@GeneratedValue(generator = "generator")

	@Column(name = "USUARIO", unique = true, nullable = false, length = 20)
	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public CuentasUsuario getCuentasUsuario() {
		return this.cuentasUsuario;
	}

	public void setCuentasUsuario(CuentasUsuario cuentasUsuario) {
		this.cuentasUsuario = cuentasUsuario;
	}

	@Column(name = "AVATAR_URL", length = 50)
	public String getAvatarUrl() {
		return this.avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

}
