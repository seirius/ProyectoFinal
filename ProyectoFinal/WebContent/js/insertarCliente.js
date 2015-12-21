function checkObligatorio(element) {
	if (element.checkMinLen(0)) {
		element.addInputState("success", 0);
		return true;
	} else {
		element.addInputState("error", 0);
		return false;
	}
}

$("#cod_cliente").blur(function () {
	checkObligatorio($(this));
});

$("#razon_social").blur(function () {
	checkObligatorio($(this));
});

$("#telefono").blur(function () {
	checkObligatorio($(this));
});

$("#direccion").blur(function () {
	checkObligatorio($(this));
});

function crearCliente() {
	var ok = true;
	
	if (!checkObligatorio($("#cod_cliente"))) ok = false;
	if (!checkObligatorio($("#razon_social"))) ok = false;
	if (!checkObligatorio($("#telefono"))) ok = false;
	if (!checkObligatorio($("#direccion"))) ok = false;
	
	return ok;
}


function eliminarCliente() {
	var ok = true;
	
	if (!checkObligatorio($("#cod_cliente"))) ok = false;
	
	return ok;
}