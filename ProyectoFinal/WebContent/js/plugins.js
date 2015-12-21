//Comprobar longitud
$.fn.equalLengthTo = function (length) {
    var value = this.val();
    if (value.length === length)
        return true;
    return false;
}

//Comprobar longitud minima
$.fn.checkMinLen = function (minLen) {
    if (this.val().length > minLen)
        return true;

    return false;
}

//Comprobar si es numerico
$.fn.checkNumerico = function () {
    if ($.isNumeric(this.val()))
        return true;

    return false;
}

//Añadir "error" o "ok" a un inputText
$.fn.addInputState = function (estado, textos) {
    var parent = this.parent();

    parent.removeClass("has-error has-success");

    var span = parent.find(".glyphicon");
    var text = parent.find(".label.label-danger");
    span.removeClass("hidden glyphicon-remove glyphicon-ok");
    if (estado === "success") {
        parent.addClass("has-success");
        span.addClass("glyphicon-ok");
        $(text[textos]).addClass("hidden");
    } else if (estado === "error") {
        parent.addClass("has-error");
        span.addClass("glyphicon-remove");
        $(text[textos]).removeClass("hidden");
    } else {
        $(text[textos]).addClass("hidden");
    }
}


//Check Email
$.fn.checkEmail = function () {
    var value = this.val();
    var regular_expression = /^([\da-z_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;

    if (regular_expression.test(value)) {
        return true;
    }

    return false;
}

//Obligatorio y numerico check
$.fn.checkObligatorioNumerico = function () {
    if (this.val().length > 0) {
        if (this.checkNumerico()) {
            this.addInputState("success", 0);
            this.addInputState("success", 1);
            return true;
        } else {
            this.addInputState("error", 0);
            return false;
        }
        
    } else {
        this.addInputState("error", 1);
        return false;
    }
}

//Checkbox isChecked?
$.fn.checkCheckBox = function () {
    if (this.is(":checked")) {
        return true;
    }
    return false;
}


