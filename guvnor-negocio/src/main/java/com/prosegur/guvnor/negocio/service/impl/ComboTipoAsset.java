package com.prosegur.guvnor.negocio.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.prosegur.guvnor.negocio.front.Opcion;
import com.prosegur.guvnor.negocio.service.AssetsTypes;

public class ComboTipoAsset extends Combo {

	@Override
	protected List<Opcion> crearOpciones() {
		List<Opcion> listaOpciones = new ArrayList<>();
		for (AssetsTypes assetsTypes : AssetsTypes.values()) {
			if (assetsTypes.isVisible()) {
				listaOpciones.add(new Opcion(assetsTypes.getNombre(), assetsTypes.getNombre()));
			}
		}
		return listaOpciones;
	}
	
	@Override
	protected void setClave() {
		clave = generaClave(tipo);
	}		

}
