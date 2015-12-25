<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="main.bbdd_handlers.PostInfo"%>
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
	<title>Dark Sky - Foro</title>
	<meta name="viewport" content="width=device-width, initial-scale = 1" />
	<link rel="stylesheet" href="<%= rootPath %>/css/bootstrap.css">
	<link rel="stylesheet" href="<%= rootPath %>/css/testCSS.css">
	<link rel="stylesheet" href="<%= rootPath %>/css/myCSS.css">
	<link rel="stylesheet" href="<%= rootPath %>/css/myFonts.css" />
	<link rel="stylesheet" href="<%= rootPath %>/css/jquery-ui.css" />
	<script src="<%= rootPath %>/js/jquery.js"></script>
	<script src="<%= rootPath %>/js/jquery-ui.js"></script>
	<script src="<%= rootPath %>/js/bootstrap.js"></script>
</head>
<body>
	<%
	HttpSession userSession = request.getSession();
	String usuario = (String) userSession.getAttribute("usuario");
	
	Connection connection = null;
	
	try {
		InitCon init = new InitCon(application);
		connection = init.getConnection();
		PostInfo postInfo = new PostInfo(connection);
		ResultSet posts = postInfo.getAllPosts();
		
	
	%>
	<div id="login-avatar" class="fixed">
		<img src="<%= rootPath %>/img/Raw/Avatar/rawAvatar.png" alt="avatarImage" id="avatarLogin" />
	</div>
	<div id="login-box">
		<div id="sliding-login-box">
			<div id="sliding-login-box-fondo"></div>
			<%
			if (usuario == null) {
			%>
			<form action="<%= rootPath %>/IniciarSesion?page=foro" method="POST">
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
			<form action="<%= rootPath %>/CerrarSesion?page=foro" method="POST">
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
						<li>
							<a href="<%= rootPath %>/jsps/index.jsp">Pagina Principal</a>
						</li>
						<li class="myActive">
							<a href="<%= rootPath %>/jsps/foro.jsp">Foro</a>
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
							<div id="contenedorForoLista">
								<div id="contenedorForoListaFondo"></div>
								<div id="idLista">
									<div class="table-condensed">
										<table class="table">
											<thead>
												<tr>
													<th>Titulo</th>
													<th>Fecha de creacion</th>
												</tr>
											</thead>
											<tbody>
												<%
												boolean hay = posts.next();
												while(hay) {
													
												%>
												<tr>
													<td><a href="#"><%= posts.getString("TITULO") %></a></td>
													<td><%= posts.getTimestamp("FECHA_CREACION") %></td>
												</tr>
												<%
													hay = posts.next();
												}
												%>
											</tbody>
										</table>
									</div>
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
		e.printStackTrace();
	} finally {
		if (connection != null) connection.close();
	}
	%>
	<script src="<%= rootPath %>/js/myScript.js"></script>
</body>
</html>