package com.prosegur.guvnor.negocio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.prosegur.guvnor.negocio.service.AppService;

@RestController
public class AppRestController {

	@Autowired
	private AppService appService;
	
	@GetMapping(MappingsController.AJAX_CARGAR_VARIABLE)
    public ResponseEntity<?> getVariableBBDD(@PathVariable String fuente, 
    										 @PathVariable String entorno, 
    										 @PathVariable String paquete, 
    										 @PathVariable String tipo, 
    										 @PathVariable String variable) {
    	String datos = appService.getVariable(fuente, entorno, paquete, tipo, variable);
        return ResponseEntity.ok(datos);
    }    
}
