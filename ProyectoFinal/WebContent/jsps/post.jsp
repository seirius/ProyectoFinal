<%@page import="java.sql.ResultSet"%>
<%@page import="main.bbdd_handlers.PostComments"%>
<%@page import="main.bbdd_objects.Post"%>
<%@page import="main.bbdd_handlers.PostInfo"%>
<%@page import="main.util.ErrorNoLogico"%>
<%@page import="java.sql.SQLException"%>
<%@page import="bbdd.MySQLConnection"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%
	Connection connection = null;
	String mysql_url = application.getInitParameter("mysql_url");
	String usuario = application.getInitParameter("mysql_usuario");
	String pw = application.getInitParameter("mysql_pw");
	
	try {
		MySQLConnection msql = new MySQLConnection(mysql_url, usuario, pw);
		connection = msql.getConnection();
		
		PostInfo postInfo = new PostInfo(connection);
		int id = Integer.parseInt(request.getParameter("id"));
		Post post = postInfo.getSinglePost(id);
		
		PostComments postComments = new PostComments(connection);
		ResultSet comments = postComments.getCommentsByPostID(id);
		boolean hay = comments.next();
	%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><%= post.titulo %></title>
	<meta name="viewport" content="width=device-width, initial-scale = 1" />
	<link rel="stylesheet" href="../css/bootstrap.css">
	<link rel="stylesheet" href="../css/myCSS.css">
	<link rel="stylesheet" href="../css/testCSS.css">
	<link rel="stylesheet" href="../css/myFonts.css" />
	<script src="../js/jquery-1.11.3.js"></script>
	<script src="../js/bootstrap.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<h2 class="text-center"><%= post.titulo %></h2>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2">
				<div class="panel panel-default">
					<div class="panel-body"><%= post.texto %></div>
				</div>
			</div>
		</div>
		<%
		while (hay) {
			String commentText = comments.getString("TEXTO");
		%>
		<div class="row topSpace-1">
			<div class="col-lg-8 col-lg-offset-2">
				<div class="panel panel-default">
					<div class="panel-body"><%= commentText %></div>
				</div>
			</div>
		</div>
		<%
			hay = comments.next();
		}
		%>
	</div>
	<%
	} catch(SQLException e) {
		%>
		<h2><%= e.getMessage() %></h2>
		<%
	} /*catch(ErrorNoLogico e) {
		%>
		<h2><%= e.getMessage() %></h2>
		<%
	}*/ finally {
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