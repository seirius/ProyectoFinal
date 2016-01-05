<%@page import="java.util.Collections"%>
<%@page import="main.util.DateComparator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.HibernateException"%>
<%@page import="main.modelo.PostComments"%>
<%@page import="java.util.Set"%>
<%@page import="main.controlador.PostInfoControl"%>
<%@page import="main.modelo.PostInfo"%>
<%@page import="org.hibernate.cfg.Configuration"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String rootPath = request.getContextPath();
	int idPost = Integer.parseInt(request.getParameter("idPost"));
	HttpSession userSession = request.getSession();
	String usuario = (String) userSession.getAttribute("usuario");
	String avatarURL = (String) userSession.getAttribute("avatarURL");
	if (avatarURL == null)
		avatarURL = "/img/Raw/Avatar/rawAvatar.png";
	
	SessionFactory factory = null;
	try {
		factory = new Configuration().configure("/main/resources/hibernate.cfg.xml").buildSessionFactory();
		
		PostInfoControl postControl = new PostInfoControl(factory);
		PostInfo post = postControl.getPostComentarios(idPost);
		Set<PostComments> setCom = post.getPostCommentses();
		List<PostComments> comentarios = new ArrayList<PostComments>();
		comentarios.addAll(setCom);
		Collections.sort(comentarios, new DateComparator());
%>
<title>Dark Sky - <%=post.getTitulo()%></title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale = 1" />
<link rel="stylesheet" href="<%=rootPath%>/css/bootstrap.css" />
<link rel="stylesheet" href="<%=rootPath%>/css/myFonts.css" />
<link rel="stylesheet" href="<%=rootPath%>/css/jquery-ui.css" />
<link rel="stylesheet" href="<%=rootPath%>/css/generalCSS.css" />
<link rel="stylesheet" href="<%=rootPath%>/css/foroCSS.css" />
<link rel="stylesheet" href="<%=rootPath%>/css/postCSS.css" />
<script src="<%=rootPath%>/js/jquery.js"></script>
<script src="<%=rootPath%>/js/jquery-ui.js"></script>
<script src="<%=rootPath%>/js/bootstrap.js"></script>
</head>
<body>
	<!-- CAJA-IMAGEN-AVATAR -->
	<div id="caja-imagen-avatar" class="position-fixed">
		<img src="<%=rootPath%><%=avatarURL%>" alt="imagenAvatar" id="imagenAvatar" />
	</div>

	<div class="position-fixed" id="caja-login">
		<div class="extend-to-parent position-relative" id="caja-login-sliding">
			<div class="extend-to-parent position-absolute" id="caja-login-sliding-fondo"></div>
			<%
				if (usuario == null) {
			%>
			<form action="<%=rootPath%>/IniciarSesion?page=post.jsp?idPost=<%=idPost%>" method="POST">
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
					<a href="<%=rootPath%>/jsps/crearCuentaUsuario.jsp">Crear cuenta</a>
				</div>
			</form>
			<%
				} else {
			%>
			<form action="<%=rootPath%>/CerrarSesion?page=post.jsp?idPost=<%=idPost%>" method="POST">
				<div class="row">
					<h3 class="text-center"><%=usuario%></h3>
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
							<a href="<%=rootPath%>/jsps/principal.jsp">Pagina Principal</a>
						</li>
						<li class="myActive">
							<a href="<%=rootPath%>/jsps/foro_principal.jsp">Foro</a>
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

				<!-- POST TITULO/TEXTO -->
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading post-titulo"><%=post.getTitulo()%>
							by
							<%=post.getCuentasUsuario().getUsuario()%></div>
						<div class="panel-body post-body"><%=post.getTexto()%></div>
					</div>
				</div>

				<!-- POST BOTON/COMENTAR -->

				<%
					if (usuario != null) {
				%>
				<div class="col-lg-offset-2 col-lg-8">
					<button class="btn-pixel btn-block" id="botonComentar" data-toggle="collapse" data-target="#cajaComentarPost">Comentar</button>
				</div>
				<%
					}
				%>

				<div class="collapse col-xs-12" id="cajaComentarPost">
					<form action="<%=rootPath%>/PublicarComentario?idPost=<%=idPost%>" method="POST">
						<div class="margin-top-2 col-lg-offset-2 col-lg-8">
							<textarea class="form-control" name="textareaComentarioPost" id="textareaComentarioPost" maxLength="400" rows="8"></textarea>
						</div>
						<div class="col-lg-4 col-lg-offset-4 margin-top-2">
							<button type="submit" class="btn-pixel btn-block btn-pixel-blue" id="botonPublicarComentario">Publicar</button>
						</div>
					</form>
				</div>

				<!-- POST COMENTARIOS DEL POST -->
				<%
					for (PostComments comentario: comentarios) {
				%>
				<div class="col-lg-12">
					<div class="panel panel-default margin-top-2">
						<div class="panel-body post-comment">
							<div class="col-lg-8"><%=comentario.getTexto()%></div>
							<div class="col-lg-4">
								<%=comentario.getFechaCreacion()%>
								|| Publicado por
								<%=comentario.getCuentasUsuario().getUsuario()%>
							</div>
						</div>
					</div>
				</div>
				<%
						}
				%>
			</div>
		</div>
	</div>
	<%
		} catch (HibernateException e) {
			%>
			<h2><%=e.getMessage()%></h2>
			<%
			e.printStackTrace();
		} finally {
			if (factory != null) factory.close();
		}
	%>
	<script src="<%=rootPath%>/js/generalScript.js"></script>
</body>
</html>