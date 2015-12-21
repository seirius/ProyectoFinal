$("#usuarioID").blur(function () {
	var element = $(this);
	if (element.checkMinLen(0)) {
		element.addInputState("success", 0);
	} else {
		element.addInputState("error", 0);
	}
});

$("#pwID").blur(function () {
	var element = $(this);
	if (element.checkMinLen(0)) {
		element.addInputState("success", 0);
	} else {
		element.addInputState("error", 0);
	}
});

$("#pwID_R").blur(function () {
	var element = $(this);
	if (element.checkMinLen(0)) {
		element.addInputState("success", 0);
	} else {
		element.addInputState("error", 0);
	}
	
	var pw = $("#pwID");
	if (element.val() === pw.val() && element.checkMinLen(0)) {
		element.addInputState("success", 1);
	} else {
		element.addInputState("error", 1);
	}
});

function crearCuenta() {
	var ok = true;
	
	var element = $("#usuarioID");
	if (element.checkMinLen(0)) {
		element.addInputState("success", 0);
	} else {
		element.addInputState("error", 0);
		ok = false;
	}
	
	element = $("#pwID");
	if (element.checkMinLen(0)) {
		element.addInputState("success", 0);
	} else {
		element.addInputState("error", 0);
		ok = false;
	}
	
	element = $("#pwID_R");
	if (element.checkMinLen(0)) {
		element.addInputState("success", 0);
	} else {
		element.addInputState("error", 0);
		ok = false;
	}
	
	element = $("#pwID_R");
	if (element.val() === $("#pwID").val() && element.checkMinLen(0)) {
		element.addInputState("success", 1);
	} else {
		element.addInputState("error", 1);
		ok = false;
	}
	
	return ok;
}


