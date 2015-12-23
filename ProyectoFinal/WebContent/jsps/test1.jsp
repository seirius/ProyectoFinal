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
	<title>Test1</title>
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
	String usuario = (String) request.getSession().getAttribute("usuario");
	
	%>
	<p><%= usuario %></p>
</body>
</html>