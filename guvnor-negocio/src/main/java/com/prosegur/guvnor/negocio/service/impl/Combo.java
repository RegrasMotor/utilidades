package com.prosegur.guvnor.negocio.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosegur.guvnor.negocio.front.Opcion;
import com.prosegur.guvnor.negocio.utils.RestUtil;

public abstract class Combo {
	private static Logger logger = LoggerFactory.getLogger(Combo.class);
	
	protected static Map<Combo, List<Opcion>> cache = new HashMap<>();
	
	protected String clave;	
	protected boolean permanente = false;
	protected RestUtil restUtil;
	
	protected String entorno;
	protected String tipo;
	protected String id;
	protected String idAux;
	
	public void setCombo(RestUtil restUtil, String entorno, String tipo, String id, String idAux) {
		this.restUtil = restUtil;
		this.entorno = entorno;
		this.tipo = tipo;
		this.id = id;
		this.idAux = idAux;
		setClave();
	}
	
	public void setPermanente(boolean permanente) {
		this.permanente = permanente;
	}
	
	public String getClave() {
		return this.clave;
	}
	
	public List<Opcion> getOpciones() {
		return getOpciones(false);
	}
	
	public void recargarCache() {
		getOpciones(true);
	}
	
	public static Map<Combo, List<Opcion>> getCache() {
		return cache;
	}
	
	protected List<Opcion> getOpciones(boolean recargar) {
		List<Opcion> opciones = null;
		synchronized(cache) {
			opciones = recargar ? null : getOpcionesCache();
			if (!permanente && opciones == null) {
				opciones = crearOpciones();
				guardaOpcionesCache(opciones);
			}		
		}
		return opciones;
	}	
	
	protected abstract List<Opcion> crearOpciones();
	
	protected String generaClave(String...parametros) {
		String clave = "";
		for (String parametro : parametros) {
			clave += "/" + (parametro==null || parametro.equals("") ? "#" : parametro);
		}
		return clave;
	}
	
	protected void setClave() {
		clave = generaClave(tipo, entorno, id, idAux);
	}	
	
	protected List<Opcion> getOpcionesCache() {
		return cache.get(this);
	}
	
	protected void guardaOpcionesCache(List<Opcion> opciones) {
		cache.put(this, opciones);
		logger.info("Generada cache para: {}", this.clave);
	}
	
	protected List<Opcion> convertirAListaOpciones(List<String> lista) {
		List<Opcion> listaOpciones = new ArrayList<>();
		for (String texto : lista) {
			listaOpciones.add(new Opcion(texto, texto));
		}
		return listaOpciones;
	}	
	
	/*public boolean isGuardaEnCache() {
		return guardaEnCache;
	}

	public void setGuardaEnCache(boolean guardaEnCache) {
		this.guardaEnCache = guardaEnCache;
	}*/
	
	/*protected RestUtil getRestUtil() {
		return restUtil;
	}	
	
	protected String getEntorno() {
		return entorno;
	}

	protected String getTipo() {
		return tipo;
	}

	protected String getId() {
		return id;
	}

	protected String getIdAux() {
		return idAux;
	}*/
	
	@Override
	public int hashCode() {
		return clave.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Combo other = (Combo)obj;
		return this.toString().equals(other.toString());
	}

	@Override
	public String toString() {
		return clave;
	}
	
}
