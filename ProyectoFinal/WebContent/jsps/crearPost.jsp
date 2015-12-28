<%@page import="main.util.UtilDates"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="main.bbdd_handlers.PostInfo"%>
<%@page import="java.sql.SQLException"%>
<%@page import="main.connection.InitCon"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%
	String rootPath = request.getContextPath();
	%>
	<title>Dark Sky - Crear Entrada</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale = 1" />
	<link rel="stylesheet" href="<%= rootPath %>/css/bootstrap.css" />
	<link rel="stylesheet" href="<%= rootPath %>/css/myFonts.css" />
	<link rel="stylesheet" href="<%= rootPath %>/css/jquery-ui.css" />
	<link rel="stylesheet" href="<%= rootPath %>/css/generalCSS.css" />
	<link rel="stylesheet" href="<%= rootPath %>/css/foroCSS.css" />
	<link rel="stylesheet" href="<%= rootPath %>/css/foroCrearPostCSS.css" />
	<script src="<%= rootPath %>/js/jquery.js"></script>
	<script src="<%= rootPath %>/js/jquery-ui.js"></script>
	<script src="<%= rootPath %>/js/bootstrap.js"></script>
</head>
<body>
	<%
	Connection connection = null;
	InitCon init = new InitCon(application);
	
	try {
		connection = init.getConnection();
		HttpSession userSession = request.getSession();
		String usuario = (String) userSession.getAttribute("usuario");
		String avatarURL = (String) userSession.getAttribute("avatarURL");
		if (avatarURL == null) avatarURL = "/img/Raw/Avatar/rawAvatar.png";
	%>
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
			<form action="<%= rootPath %>/IniciarSesion?page=crearPost.jsp" method="POST">
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
			<form action="<%= rootPath %>/CerrarSesion?page=crearPost.jsp" method="POST">
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
				<%
				if (usuario != null) {
				%>
				<form action="<%= rootPath %>/CrearPost" method="POST">
					<div class="col-lg-12 form-group">
						<label for="tituloPost">Titulo</label>
						<input type="text" name="tituloPost" class="form-control" id="inputTitulo" />
					</div>
					<div class="col-lg-12">
						<label for="textoPost">Texto</label>
						<textarea name="textoPost" class="form-control" id="textoPost" maxLength = "1000" rows="20"></textarea>
					</div>
					<div class="col-lg-4 col-lg-offset-4 margin-top-3 margin-bot-3">
						<button type="submit" class="btn-pixel btn-block">Publicar</button>
					</div>
				</form>
				<%
				} else {
					
				%>
				<h2 class="text-center">Tienes que iniciar sesion para crear una nueva entrada!</h2>
				<%
				}
				%>
			</div>
		</div>
	</div>
	<%
	} catch(SQLException e) {
		%>
		<h2><%= e.getMessage() %></h2>
		<%
	} finally {
		try {
			if (connection != null) connection.close();
		} catch(SQLException e) {
			%>
			<h2><%= e.getMessage() %></h2>
			<%	
		}
	}
	%>
	<script src="<%= rootPath %>/js/generalScript.js"></script>
</body>
</html>