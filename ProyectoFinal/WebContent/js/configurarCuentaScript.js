$(function () {
	$("#seleccion-avatar").selectable({
        selecting: function(event, ui){
            if( $(".ui-selected, .ui-selecting").length > 1){
                  $(ui.selecting).removeClass("ui-selecting");
            }
        }
    });
});