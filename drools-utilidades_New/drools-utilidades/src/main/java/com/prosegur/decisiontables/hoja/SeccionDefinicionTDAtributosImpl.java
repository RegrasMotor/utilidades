package com.prosegur.decisiontables.hoja;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.drools.ide.common.client.modeldriven.dt52.AttributeCol52;
import org.drools.ide.common.client.modeldriven.dt52.GuidedDecisionTable52;

import com.prosegur.decisiontables.Dato;
import com.prosegur.decisiontables.tipos.TipoBaseColumna;
import com.prosegur.decisiontables.tipos.TipoColumna;
import com.prosegur.excel.Fila;
import com.prosegur.excel.Hoja;

public class SeccionDefinicionTDAtributosImpl extends SeccionDefinicionTD {

	@Override
	public void crear(GuidedDecisionTable52 tablaDecision, Hoja hoja) {
		Set<TipoColumna> tiposColumna = new LinkedHashSet<>(Arrays.asList(TipoColumna.values()));
		for (AttributeCol52 columna:tablaDecision.getAttributeCols()) {
			if (columna != null) {
				Fila fila = crearCeldaTitulo(hoja, true, columna.getAttribute());
				crearCeldaDato(fila, Boolean.toString(columna.isHideColumn()), 
						 	 	  	 Boolean.toString(columna.isReverseOrder()), 
						 	 	  	 Boolean.toString(columna.isUseRowNumber()), 
						 	 	  	 Dato.getValor(columna.getDefaultValue()));
				tiposColumna.remove(TipoColumna.getTipoColumna(columna.getAttribute()));
			}
		}	
		for (TipoColumna tipoColumna: tiposColumna) {
			if(tipoColumna.getTipoBaseColumna().equals(TipoBaseColumna.ATTRIBUTE)) {
				Fila fila = crearCeldaTitulo(hoja, false, tipoColumna.getNombre());
				crearCeldaDato(fila, "", "", "", "");
			}
		}
	}

	@Override
	public void leer(Hoja hoja, GuidedDecisionTable52 tablaDecision) {
		List<AttributeCol52> atributos = procesarSeccion(hoja, filaSeccion.numeroDeFila(), fila -> {
			AttributeCol52 atributo = new AttributeCol52();
			atributo.setWidth(-1);
			atributo.setAttribute(getString(fila, 1));
			atributo.setHideColumn(getBooleano(fila, 2));
			atributo.setReverseOrder(getBooleano(fila, 3));
			atributo.setUseRowNumber(getBooleano(fila, 4));
			String valorDefecto = getString(fila, 5);
			if (!valorDefecto.equals("")) {
				TipoColumna tipoColumna = TipoColumna.getTipoColumna(atributo.getAttribute());
				atributo.setDefaultValue(tipoColumna.getTipoDato().nuevoCellValue(valorDefecto));
			}
			return atributo;
		});
		
		tablaDecision.setAttributeCols(atributos);
	}

}
