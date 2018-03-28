package com.prosegur.guvnor.negocio.service;

import java.util.Date;
import java.util.List;

import com.prosegur.guvnor.negocio.front.Opcion;

public interface ComboService {
	
	List<Opcion> getCombo(String entorno, String tipo) throws InstantiationException, IllegalAccessException;
	
	List<Opcion> getCombo(String entorno, String tipo, String id) throws InstantiationException, IllegalAccessException;
	
	List<Opcion> getCombo(String entorno, String tipo, String id, String idAux) throws InstantiationException, IllegalAccessException;
	
	String getTiempoRecargarCache();
	
	Date getFechaUltimoCacheo();

}