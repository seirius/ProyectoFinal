<%@page import="main.util.UtilDates"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="main.bbdd_handlers.PostInfo"%>
<%@page import="bbdd.MySQLConnection"%>
<%@page import="main.util.ErrorNoLogico"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Foro de Dark Sky</title>
	<meta name="viewport" content="width=device-width, initial-scale = 1" />
	<link rel="stylesheet" href="../css/bootstrap.css">
	<link rel="stylesheet" href="../css/myCSS.css">
	<link rel="stylesheet" href="../css/testCSS.css">
	<link rel="stylesheet" href="../css/myFonts.css" />
	<script src="../js/jquery-1.11.3.js"></script>
	<script src="../js/bootstrap.js"></script>
</head>
<body>
	<%
	Connection connection = null;
	String mysql_url = application.getInitParameter("mysql_url");
	String usuario = application.getInitParameter("mysql_usuario");
	String pw = application.getInitParameter("mysql_pw");
	
	try {
		MySQLConnection msql = new MySQLConnection(mysql_url, usuario, pw);
		connection = msql.getConnection();
	%>
	<div class="container">
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2">
				<h3 class="text-center">Foro</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2">
				<table class="table table-hover">
					<thead>
						<tr>
							<th class="col-lg-6">Titulo</th>
							<th class="col-lg-6">Fecha de creacion</th>
						</tr>
					</thead>
					<tbody>
					<%
					PostInfo postInfo = new PostInfo(connection);
					ResultSet posts = postInfo.getAllPosts();
					boolean hay = posts.next();
					while(hay) {
						int id = posts.getInt("ID");
						String titulo = posts.getString("TITULO");
						String fecha = UtilDates.timestampToString(posts.getTimestamp("FECHA_CREACION"), "dd/MM/yy HH:mm:ss");
					%>
						<tr>
							<th><a href="post.jsp?id=<%= id %>"><%= titulo %></a></th>
							<th><%= fecha %></th>
						</tr>
					<%
						hay = posts.next();
					}
					%>
					</tbody>
				</table>
			</div>
		</div>
		<div class="row topSpace-3">
			<div class="col-lg-8 col-lg-offset-2">
				<div class="cajaComentario">
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Cupiditate sint quod architecto neque quae placeat cumque asperiores saepe
						maiores hic id quam ratione tempora ipsum temporibus est quos totam laborum?</p>
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
</body>
</html>