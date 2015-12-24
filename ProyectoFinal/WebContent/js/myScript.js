$("#avatarLogin").click(function () {
	var element = $(this);
	
	var loginBox = $("#login-box");
	var zIndex = loginBox.css("z-index");
	if (zIndex === "-1") loginBox.css("z-index", "9");
	else loginBox.css("z-index", "-1");
	
	$("#sliding-login-box").toggle("slide", {direction: "right"});
	
	if ($("#login-avatar").css("right") === "10px") $("#login-avatar").animate({right: '+=390px'});
	else $("#login-avatar").animate({right: "10px"});
});


