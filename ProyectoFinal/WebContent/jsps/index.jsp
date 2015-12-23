<%@page import="main.connection.InitCon"%>
<%@page import="main.bbdd_handlers.PostComments"%>
<%@page import="main.bbdd_handlers.PostInfo"%>
<%@page import="bbdd.MySQLConnection"%>
<%@page import="main.util.ErrorNoLogico"%>
<%@page import="main.noticias_portada.Noticia"%>
<%@page import="java.util.ArrayList"%>
<%@page import="main.noticias_portada.NoticiasPortada"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Dark Sky - Principal</title>
	<meta name="viewport" content="width=device-width, initial-scale = 1" />
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/testCSS.css">
	<link rel="stylesheet" href="css/myCSS.css">
	<link rel="stylesheet" href="css/myFonts.css" />
	<link rel="stylesheet" href="css/jquery-ui.css" />
	<script src="js/jquery-1.11.3.js"></script>
	<script src="js/jquery-ui.js"></script>
	<script src="js/bootstrap.js"></script>
</head>
<body>
	<%
	Connection connection = null;
	InitCon init = new InitCon(application);
	
	try {
		connection = init.getConnection();
	%>
	<div id="login-avatar" class="fixed">
		<img src="img/Raw/Avatar/rawAvatar.png" alt="avatarImage" id="avatarLogin" />
	</div>
	<div id="login-box">
		<div id="sliding-login-box"></div>
	</div>
	<div class="container-fluid">
		<div class="row">
			<div id="titulo" class="bloque text-center">
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
							<a href="#">Portada</a>
						</li>
						<li class="myActive">
							<a href="#">Pagina Principal</a>
						</li>
						<li>
							<a href="#">Foro</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row">
			<div id="fondo">
				<div class="container">
					<div class="row">
						<div id="contenido">
							<div id="contenidoFondo"></div>
							<div id="contenedorCajas" class="col-lg-8">
								<%
								NoticiasPortada noticias_portada = new NoticiasPortada(connection);
								ArrayList<Noticia> noticias = noticias_portada.getNoticias();
								for (Noticia noticia: noticias) {
									%>
								<div class="caja">
									<div class="cajaFondo"></div>
									<div class="col-lg-5 col-xs-5 imgCaja">
										<img class="imagenClase" src="<%= noticia.urlImagen %>" alt="<%= noticia.titulo %>" />
									</div>
									<div class="textoCaja col-lg-7 col-xs-7">
										<h3 class="text-center"><%= noticia.titulo %></h3>
										<p><%= noticia.texto %></p>
									</div>
								</div>
								<%
								}
								%>
							</div>
							<div id="contenedorExtra" class="col-lg-4">
								<div class="cajaExtra">
									<div class="cajaExtraFondo"></div>
								</div>
								<div class="cajaExtra">
									<div class="cajaExtraFondo"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%
	} catch(SQLException e) {
		%>
		<h2><%= e.getMessage() %></h2>
		<%
	} catch(ErrorNoLogico e) {
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
	<script src="js/myScript.js"></script>
</body>
</html>