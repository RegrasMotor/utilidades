package com.prosegur.guvnor.negocio.service;

public enum AssetsTypes {
	PACKAGE(true),
	GDST(true),
	DRL(true),
	JAR(false),
	FUNCTION(false),
	SCENARIO(false),
	ENUMERATION(true);
	
	private String nombre;
	private boolean visible;
	
	private AssetsTypes(boolean visible) {
		this.visible = visible;
		this.nombre = this.name().toLowerCase();
	}
	
	public String getNombre() {
		return nombre;
	}

	public boolean isVisible() {
		return visible;
	}
	
	public static AssetsTypes getAssetsTypes(String nombre) {
		return AssetsTypes.valueOf(nombre.toUpperCase());
	}
}
