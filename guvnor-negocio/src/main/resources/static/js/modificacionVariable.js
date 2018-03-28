$(function() {
	var regEntornoHasta = $("#regEntornoHasta");
	var regAsset = $("#regAsset");
	var regCodigo = $("#regCodigo");
	var regPaquetesHasta = $("#regPaquetesHasta");
	
	var habilitarBotonGuardar = function() {
		habilitar("btnGuardarGuvnor", regPaquetesHasta.find(".list-group-item.active input").length > 0);
	};
	
  	var cargalistaPaquetes = function() {
  		var parametros = "entorno=" + regEntornoHasta.val() + "&id=nada&tipoLista=lista";
  		regPaquetesHasta.load("/ajax/combo/paquete", parametros, function(response, status, xhr) {
  	  		if (status === "success") {
  	  			renderCheckList("#regPaquetesHasta", habilitarBotonGuardar);
  	  		}
		});
  	};	
	regEntornoHasta.on("change", cargalistaPaquetes);
	cargalistaPaquetes();
	
	regAsset.on("change", function() {
		var valor = $.parseJSON(regAsset.val());
		var vacio = (valor === "VACIO");
		habilitar("btnCargarBBDD", vacio?!vacio:valor);
		habilitar("btnCargarGuvnor", !vacio);
		habilitar("divCodigo", false);
		regCodigo.val("");	
		habilitar("btnGuardarGuvnor", false);
	});
	
	var cargarCodigo = function(desde) {
		$('#btnCargar' + desde).on('click', function(event) {
			$.get("/ajax/variable/" + desde.toUpperCase() + "/" + $("#regEntornoDesde").val() + "/" + $("#regPaquete").val() + "/" + $("#regTipoAsset").val() + "/" + getOptionText(regAsset), function(data) {
				regCodigo.val(data);
				habilitar("divCodigo", true);
				habilitarBotonGuardar();
			});
		});
	};
	cargarCodigo("BBDD");
	cargarCodigo("Guvnor");
	
    $('#btnGuardarGuvnor').on('click', function(event) {
    	var paquetes = regPaquetesHasta.find(".list-group-item.active").map(function() {
    		return $(this).text();
        }).get();
    	
    	var parametros = {
			paquetes: paquetes,
			asset: getOptionText(regAsset),
			codigo: regCodigo.val()
    	};
  		$("#popupCuerpo").load("/ajax/guardarGuvnor/" + regEntornoHasta.val(), parametros, function(response, status, xhr) {
  	  		if (status === "success") {
  	  			$('#popup').modal('show');
  	  		}  			
		});        
	});	
});