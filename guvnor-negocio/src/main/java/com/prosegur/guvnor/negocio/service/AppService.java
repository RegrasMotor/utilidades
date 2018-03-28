package com.prosegur.guvnor.negocio.service;

import java.util.List;
import java.util.Map;

public interface AppService {

	String getVariable(String fuente, String entorno, String paquete, String tipo, String nombre);
	
	String getVariableBBDD(String entorno, String paquete, String tipo, String nombre);
	
	String getVariableGuvnor(String entorno, String paquete, String nombre);
	
	Map<String, Boolean> guardarGuvnor(String entorno, List<String> paquetes, String asset, String codigo);
}