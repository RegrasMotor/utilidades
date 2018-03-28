package com.prosegur.guvnor.negocio.controller;

public final class MappingsController {
	private MappingsController() {}
	
	public static final String BASE = "/";
	public static final String INICIO = "/inicio";
	public static final String LOGIN = "/login";
	public static final String ERROR_403 = "/403";
	public static final String AJAX_BASE = "/ajax/";
	public static final String AJAX_COMBO = AJAX_BASE + "combo/{tipo}";
	public static final String AJAX_GUARDAR_GUVNOR = AJAX_BASE + "guardarGuvnor/{entorno}";
	public static final String AJAX_CARGAR_VARIABLE = AJAX_BASE + "variable/{fuente}/{entorno}/{paquete}/{tipo}/{variable}";
	public static final String MODIFICACION_VARIABLE = "/modificacionVariable";
}
