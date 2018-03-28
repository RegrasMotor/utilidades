package com.prosegur.guvnor.negocio.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.dom.DOMSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import com.prosegur.guvnor.negocio.dao.SqlAsset;
import com.prosegur.guvnor.negocio.front.Opcion;
import com.prosegur.guvnor.negocio.service.AssetsTypes;
import com.prosegur.guvnor.negocio.service.QueryXPath;
import com.prosegur.guvnor.negocio.service.UrlRest;

public class ComboAsset extends Combo {
	private static Logger logger = LoggerFactory.getLogger(ComboAsset.class);

	@Override
	protected List<Opcion> crearOpciones() {
		ResponseEntity<DOMSource> response = restUtil.restCallGETGuvnor(UrlRest.ASSETS, idAux);
		List<Opcion> listaOpcionesPrincipal = generaOpcionesPorTipoAsset(response, id);
		if (!permanente) {
			for (AssetsTypes assetsTypes : AssetsTypes.values()) {
				if (assetsTypes.isVisible() && !assetsTypes.getNombre().equals(id)) {
					Combo combo = new ComboAsset();
					combo.setCombo(restUtil, entorno, tipo, assetsTypes.getNombre(), idAux);
					combo.setPermanente(true);
					List<Opcion> listaOpciones = generaOpcionesPorTipoAsset(response, assetsTypes.getNombre());
					cache.put(combo, listaOpciones);
					logger.info("Generada cache permanente para: {}", combo.getClave());
				}
			}			
		}
		return listaOpcionesPrincipal;	
	}
	
	private List<Opcion> generaOpcionesPorTipoAsset(ResponseEntity<DOMSource> response, String tipoAsset) {
		List<String> lista = restUtil.executeXPath(response, QueryXPath.ASSETS_NAME, tipoAsset);
		List<Opcion> listaOpciones = new ArrayList<>();
		for (String asset : lista) {
			String valor = String.valueOf(SqlAsset.existeNombre(tipoAsset, asset));
			listaOpciones.add(new Opcion(valor, asset));
		}
		return listaOpciones;		
	}	
	
	@Override
	protected void setClave() {
		clave = generaClave(tipo, entorno, idAux, id);
	}	

}
