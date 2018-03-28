package com.prosegur.guvnor.negocio.service;

public enum Motor {
	COSTEO("Motor Costeo", "BRA.costeo"),
	HORAS_PERSONAS("Motor Horas Personas", "BRA.horaspersonas");
	
	private String nombre;
	private String paquete;
	
	private Motor(String nombre, String paquete) {
		this.nombre = nombre;
		this.paquete = paquete;
	}
	public String getNombre() {
		return nombre;
	}
	public String getPaquete() {
		return paquete;
	}
	
}
