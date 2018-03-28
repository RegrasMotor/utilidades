package com.prosegur.guvnor.negocio.service;

public enum Entorno {
	HOMOLOGACION("Homologación", "restUtilHomologacion"),
	PRODUCCION("Producción", "restUtilProduccion");
	
	private String nombre;
	private String qualifierRestUtil;
	
	private Entorno(String nombre, String qualifierRestUtil) {
		this.nombre = nombre;
		this.qualifierRestUtil = qualifierRestUtil;
	}
	public String getValor() {
		return name();
	}
	public String getNombre() {
		return nombre;
	}
	public String getQualifierRestUtil() {
		return qualifierRestUtil;
	}
	
	public static Entorno getEntorno(String valor) {
		try {
			return Entorno.valueOf(valor);
		} catch (IllegalArgumentException e) {
			return Entorno.HOMOLOGACION;
		}
	}
	
}
