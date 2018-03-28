package com.prosegur.guvnor.negocio.controller;

import java.util.HashMap;
import java.util.Map;

public class RespuestaAjax {
	
    private boolean estadoValido = Boolean.TRUE;
    private Map<String, String> datos = new HashMap<>();

    public boolean getEstadoValido() {
        return estadoValido;
    }

    public void setEstadoValido(boolean estadoValido) {
        this.estadoValido = estadoValido;
    }

	public Map<String, String> getDatos() {
		return datos;
	}

	public void setDatos(Map<String, String> datos) {
		this.datos = datos;
	}
	
	public void addDato(String clave, String valor) {
		datos.put(clave, valor);
	}
    
}
