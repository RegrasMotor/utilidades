package com.prosegur.guvnor.negocio.controller;

public enum Pagina {
	INICIO(MappingsController.INICIO),
	LOGIN(MappingsController.LOGIN),
	ERROR_403("error/", MappingsController.ERROR_403),
	AJAX_COMBO("fragments/", "general :: combo"),
	AJAX_PAQUETES_HASTA("modificacionVariable :: paquetesHasta"),
	AJAX_GUARDAR_GUVNOR("fragments/", "general :: mensaje"),
	MODIFICACION_VARIABLE(MappingsController.MODIFICACION_VARIABLE);
	
	private String pagina;

	private Pagina(String mapping) {
		this("", mapping);
	}
	
	private Pagina(String ruta, String mapping) {
		this.pagina = String.format("%s%s", ruta, parseNombrePagina(mapping));
	}
	
	private String parseNombrePagina(String mapping) {
		int separador = mapping.lastIndexOf("/");
		if (separador != -1) {
			return mapping.substring(separador + 1, mapping.length());
		} else {
			return mapping;
		}
	}
	
	public String getPagina() {
		return pagina;
	}
	
}
