<%@page import="main.controlador.servlets.AvatarImgControl"%>
<%@page import="main.modelo.AvatarImg"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.cfg.Configuration"%>
<%@page import="org.hibernate.HibernateException"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String rootPath = request.getContextPath();
	HttpSession userSession = request.getSession();
	String usuario = (String) userSession.getAttribute("usuario");
	String avatarURL = (String) userSession.getAttribute("avatarURL");
	if (avatarURL == null)
		avatarURL = "/img/Raw/Avatar/rawAvatar.png";
	
	SessionFactory factory = null;
	try {
		factory = new Configuration().configure("/main/resources/hibernate.cfg.xml").buildSessionFactory();
		
		AvatarImgControl avatarControl = new AvatarImgControl(factory);
		List<AvatarImg> avatares = avatarControl.getAvatares();
%>
<title>Dark Sky - Configurar Cuenta</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale = 1" />
<link rel="stylesheet" href="<%= rootPath %>/css/bootstrap.css" />
<link rel="stylesheet" href="<%= rootPath %>/css/myFonts.css" />
<link rel="stylesheet" href="<%= rootPath %>/css/jquery-ui.css" />
<link rel="stylesheet" href="<%= rootPath %>/css/generalCSS.css" />
<link rel="stylesheet" href="<%= rootPath %>/css/crearCuentaCSS.css" />
<link rel="stylesheet" href="<%= rootPath %>/css/configurarCuentaCSS.css" />
<script src="<%= rootPath %>/js/jquery.js"></script>
<script src="<%= rootPath %>/js/jquery-ui.js"></script>
<script src="<%= rootPath %>/js/bootstrap.js"></script>
</head>
<body>
	<!-- CAJA-IMAGEN-AVATAR -->
	<div id="caja-imagen-avatar" class="position-fixed">
		<img src="<%= rootPath %><%= avatarURL %>" alt="imagenAvatar" id="imagenAvatar" />
	</div>

	<div class="position-fixed" id="caja-login">
		<div class="extend-to-parent position-relative" id="caja-login-sliding">
			<div class="extend-to-parent position-absolute" id="caja-login-sliding-fondo"></div>
			<%
			if (usuario == null) {
			%>
			<form action="<%= rootPath %>/IniciarSesion?page=configurar-cuenta.jsp" method="POST">
				<div class="form-group">
					<label for="usuarioID">Usuario</label>
					<input type="text" name="usuario" class="form-control" id="usuarioID" />
				</div>
				<div class="form-group">
					<label for="passID">Contraseña</label>
					<input type="password" name="pass" class="form-control" id="passID" />
				</div>
				<div class="text-center">
					<button type="submit" class="btn-pixel">Iniciar Sesion</button>
				</div>
				<div class="margin-top-2">
					<a href="<%= rootPath %>/jsps/crearCuentaUsuario.jsp">Crear cuenta</a>
				</div>
			</form>
			<%
			} else {
			%>
			<form action="<%= rootPath %>/CerrarSesion?page=configurar-cuenta.jsp" method="POST">
				<div class="row">
					<h3 class="text-center"><%= usuario %></h3> 
				</div>
				<div class="row text-center">
					<button type="submit" class="btn btn-primary">Cerrar Sesion</button>
				</div>
			</form>
			<%
			}
			%>
		</div>
	</div>

	<!-- CABECERA + MENU -->
	<div class="container-fluid">
		<div class="row">
			<div class="text-center" id="cabecera">
				<span>DARK </span>
				<span>SKY</span>
			</div>
			<div class="navbar navbar-inverse">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
				</div>
				<div class="collapse navbar-collapse" id="myNavbar">
					<ul class="nav navbar-nav">
						<li>
							<a href="<%= rootPath %>/jsps/principal.jsp">Pagina Principal</a>
						</li>
						<li class="myActive">
							<a href="<%= rootPath %>/jsps/foro_principal.jsp">Foro</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- CAJA GENERAL -->
	<div class="container-fluid" id="caja-general">

		<!-- CAJA CONTENIDO -->
		<div class="container position-relative no-padding margin-top-2" id="caja-contenido">
			<div class="extend-to-parent position-absolute" id="caja-contenido-fondo"></div>
			<div class="extend-to-parent position-relative" id="caja-contenido-source">
				<div class="col-lg-4 caja-bloque-configuracion">
					<span>Elige un avatar:</span>
				</div>
				<div class="col-lg-4">
					<div id="seleccion-avatar">
						<%
							for (AvatarImg avatar: avatares) {
						%>
						<div class="caja-avatar-img text-center">
							<img src="<%=rootPath%><%=avatar.getUrlAvatar()%>" alt="<%=avatar.getNombreAvatar()%>" class="avatares" />
						</div>
						<%
							}
						%>
					</div>
				</div>
				<form action="">
					<div class="col-lg-6 col-lg-offset-3 margin-top-3">
						<button type="submit" class="btn-pixel btn-pixel-blue btn-block">Guardar Cambios</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<%
	} catch(HibernateException e) {
		%>
		<h2><%= e.getMessage() %></h2>
		<%
		e.printStackTrace();
	} finally {
		if (factory != null) factory.close();
	}
	%>
	<script src="<%= rootPath %>/js/generalScript.js"></script>
	<script src="<%= rootPath %>/js/configurarCuentaScript.js"></script>
</body>
</html>