package com.prosegur.decisiontables.hoja;

import org.drools.ide.common.client.modeldriven.dt52.DescriptionCol52;
import org.drools.ide.common.client.modeldriven.dt52.GuidedDecisionTable52;
import org.drools.ide.common.client.modeldriven.dt52.RowNumberCol52;

import com.prosegur.decisiontables.tipos.TipoSeccionDefinicion;
import com.prosegur.excel.Hoja;

public class HojaTDDefinicionImpl extends HojaTD {
		
	
	@Override
	public void crear(GuidedDecisionTable52 tablaDecision, Hoja hoja) {
		// Define la columna RowNumber
		RowNumberCol52 numeroFila = new RowNumberCol52();
		numeroFila.setHideColumn(Boolean.FALSE);
		numeroFila.setWidth(-1);
		tablaDecision.setRowNumberCol(numeroFila);
		
		// Define la columna Description
		DescriptionCol52 descripcion = new DescriptionCol52();
		descripcion.setHideColumn(Boolean.FALSE);
		descripcion.setWidth(-1);
		tablaDecision.setDescriptionCol(descripcion);
		
		// Define el resto de columnas
		for(TipoSeccionDefinicion tipoSeccionDefinicion: TipoSeccionDefinicion.values()) {
			tipoSeccionDefinicion.crear(tablaDecision, hoja);
			hoja.crearFila();
		}
		
		// Ajusta el ancho de las columnas automáticamente
		hoja.anchoAutomaticoColumnasModificadas();
	}

	@Override
	public void leer(Hoja hoja, GuidedDecisionTable52 tablaDecision) {
		for(TipoSeccionDefinicion tipoSeccionDefinicion: TipoSeccionDefinicion.values()) {
			tipoSeccionDefinicion.leer(hoja, tablaDecision);
		}
	}	
}
