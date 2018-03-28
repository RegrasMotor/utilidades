package com.prosegur.guvnor.negocio.controller;

public class Mensaje {
	
	public static final String TIPO_OK = "success";
	public static final String TIPO_INFO = "info";
	public static final String TIPO_WARNING = "warning";
	public static final String TIPO_ERROR = "danger";
	
	public static final String ICONO_OK = "ok-sign";
	public static final String ICONO_INFO = "info-sign";
	public static final String ICONO_WARNING = "warning-sign";
	public static final String ICONO_ERROR = "remove-sign";
	
	private String tipo;
	private String icono;
	private String texto;
	
	public Mensaje(String tipo, String icono, String texto) {
		super();
		this.tipo = tipo;
		this.icono = icono;
		this.texto = texto;
	}
	
	public static Mensaje nuevoMensajeOK(String texto) {
		return new Mensaje(TIPO_OK, ICONO_OK, texto);
	}
	
	public static Mensaje nuevoMensajeINFO(String texto) {
		return new Mensaje(TIPO_INFO, ICONO_INFO, texto);
	}
	
	public static Mensaje nuevoMensajeWARNING(String texto) {
		return new Mensaje(TIPO_WARNING, ICONO_WARNING, texto);
	}
	
	public static Mensaje nuevoMensajeERROR(String texto) {
		return new Mensaje(TIPO_ERROR, ICONO_ERROR, texto);
	}	

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
}
