var habilitar;
var getOptionText;

$(function() {
  	$(document).ajaxSend($.showLoading);	
  	$(document).ajaxSuccess($.hideLoading).ajaxError(function(event, jqxhr, settings, thrownError) {
  		$.hideLoading();
  		$.notify({
			icon : 'glyphicon glyphicon-thumbs-down alert-icon-big',
			message : "Se ha producido un error y no se ha podido completar la operación."}, {
			placement : {
				align : "center"
			},
			type : 'danger',
			allow_dismiss : false
		});    		
  	});
  	
  	habilitar = function(id, valor, clase) {
  		var ref = $("#" + id);
  		if (valor) {
  			ref.removeClass("disable-content");
  		} else {
  			ref.addClass("disable-content");
  		}
  	};
  	
  	getOptionText = function(selector) {
  		return $(selector).find("option:selected").text();
  	};
  	
	var addParametro = function(parametros, nombre, clave) {
		if (clave) {
			return (parametros===""?"":parametros + "&") + nombre + "=" + $("#" + clave).val();
		} else {
			return parametros;
		}
	};  
	
  	var cargaComboAjax = function(entorno, tipo, padre, seleccione, id, padreAux) {
  		var combo = $("#" + id);
  		var parametros = addParametro("", "entorno", entorno);
  		parametros = addParametro(parametros, "id", padre);
  		parametros = addParametro(parametros, "idAux", padreAux);
  		combo.load("/ajax/combo/" + tipo, parametros, function(response, status, xhr) {
  	  		if (status === "success") {
  	  			combo.prepend('<option value="VACIO" selected="selected">Seleccione un valor</option>');
  	  		}
		});
  	};
  			
  	var primerCombo;
  		
	$("select[padre]").each(function(index) {
		var campo = $(this);
		var padre = campo.attr("padre");
		var cargar = function() {
			cargaComboAjax(campo.attr("entorno"), campo.attr("tipo"), padre, campo.attr('seleccione'), campo.attr('id'), campo.attr('padreAux'));
		};
	  	$("#" + padre).on("change", cargar);
	  	if (index === 0) {
	  		primerCombo = cargar;
	  	}
	  	campo.on("change", function() {
	  		campo.find("option[value='VACIO']").remove()
	  	});
	})
	
	if (primerCombo) {
		primerCombo();
	}
});