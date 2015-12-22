<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><%= request.getParameter("tituloPost") %></title>
	<meta name="viewport" content="width=device-width, initial-scale = 1" />
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/myCSS.css">
	<link rel="stylesheet" href="css/testCSS.css">
	<link rel="stylesheet" href="css/myFonts.css" />
	<script src="js/jquery-1.11.3.js"></script>
	<script src="js/bootstrap.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2">
				<h3 class="text-center"><%= request.getParameter("tituloPost") %></h3>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2">
				<span><%= request.getParameter("postText") %></span>	
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

</body>
</html>