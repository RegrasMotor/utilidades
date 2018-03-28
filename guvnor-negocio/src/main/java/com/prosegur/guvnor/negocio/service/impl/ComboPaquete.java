package com.prosegur.guvnor.negocio.service.impl;

import java.util.Collections;
import java.util.List;

import javax.xml.transform.dom.DOMSource;

import org.springframework.http.ResponseEntity;

import com.prosegur.guvnor.negocio.front.Opcion;
import com.prosegur.guvnor.negocio.service.QueryXPath;
import com.prosegur.guvnor.negocio.service.UrlRest;

public class ComboPaquete extends Combo {
	private static final String INICIO_NOMBRE_PAQUETE = "BRA.";
	
	@Override
	protected List<Opcion> crearOpciones() {
		ResponseEntity<DOMSource> response = restUtil.restCallGETGuvnor(UrlRest.PACKAGES);
		List<String> opciones = restUtil.executeXPath(response, QueryXPath.PACKAGES_NAME, INICIO_NOMBRE_PAQUETE);
		Collections.sort(opciones, String.CASE_INSENSITIVE_ORDER);
		return convertirAListaOpciones(opciones);
	}
	
	@Override
	protected void setClave() {
		clave = generaClave(tipo, entorno);
	}		

}
