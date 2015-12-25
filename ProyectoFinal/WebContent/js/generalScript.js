$("#imagenAvatar").click(function () {
	var element = $(this);
	
	var loginBox = $("#caja-login");
	var zIndex = loginBox.css("z-index");
	if (zIndex === "-1") loginBox.css("z-index", "9");
	else {
		loginBox.delay(300)
		  .queue(function (next) { 
			    $(this).css("z-index", "-1").next();
		});
	}
	
	$("#caja-login-sliding").toggle("slide", {direction: "right"});
	
	var cajaImagen = $("#caja-imagen-avatar");
	
	if (cajaImagen.css("right") === "10px") {
		cajaImagen.animate({right: "+=390px"});
		cajaImagen.animate({width: "70px"});
	}
	else {
		cajaImagen.animate({right: "10px"});
		cajaImagen.animate({width: "50px"});
	}
});