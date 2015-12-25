<%@page import="main.connection.InitCon"%>
<%@page import="java.sql.Connection"%>
<%@page import="main.util.ErrorNoLogico"%>
<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%
	String rootPath = request.getContextPath();
	%>
	<title>Dark Sky - Pagina Principal</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale = 1" />
	<link rel="stylesheet" href="<%= rootPath %>/css/bootstrap.css" />
	<link rel="stylesheet" href="<%= rootPath %>/css/myFonts.css" />
	<link rel="stylesheet" href="<%= rootPath %>/css/jquery-ui.css" />
	<link rel="stylesheet" href="<%= rootPath %>/css/generalCSS.css" />
	<link rel="stylesheet" href="<%= rootPath %>/css/principalCSS.css" />
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
	%>
	<!-- CAJA-IMAGEN-AVATAR -->
	<div id="caja-imagen-avatar" class="position-fixed">
		<img src="<%= rootPath %>/img/Raw/Avatar/rawAvatar.png" alt="imagenAvatar" id="imagenAvatar" />
	</div>
	
	<div class="position-fixed" id="caja-login">
		<div class="extend-to-parent position-relative" id="caja-login-sliding">
			<div class="extend-to-parent position-absolute" id="caja-login-sliding-fondo"></div>
			<%
			if (usuario == null) {
			%>
			<form action="<%= rootPath %>/IniciarSesion?page=principal" method="POST">
				<div class="form-group">
					<label for="usuarioID">Usuario</label>
					<input type="text" name="usuario" class="form-control" id="usuarioID" />
				</div>
				<div class="form-group">
					<label for="passID">Contraseña</label>
					<input type="password" name="pass" class="form-control" id="passID" />
				</div>
				<div class="text-center">
					<button type="submit" class="btn btn-primary">Iniciar Sesion</button>
				</div>
			</form>
			<%
			} else {
			%>
			<form action="<%= rootPath %>/CerrarSesion?page=principal" method="POST">
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
						<li class="myActive">
							<a href="<%= rootPath %>/jsps/principal.jsp">Pagina Principal</a>
						</li>
						<li>
							<a href="<%= rootPath %>/jsps/foro.jsp">Foro</a>
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
		</div>
	</div>
	<script src="<%= rootPath %>/js/generalScript.js"></script>
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
</body>
</html>








