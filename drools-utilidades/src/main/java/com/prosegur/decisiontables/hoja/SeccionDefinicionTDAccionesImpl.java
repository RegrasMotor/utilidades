package com.prosegur.decisiontables.hoja;

import java.util.List;

import org.drools.ide.common.client.modeldriven.dt52.ActionCol52;
import org.drools.ide.common.client.modeldriven.dt52.ActionSetFieldCol52;
import org.drools.ide.common.client.modeldriven.dt52.GuidedDecisionTable52;

import com.prosegur.decisiontables.Dato;
import com.prosegur.decisiontables.tipos.TipoDato;
import com.prosegur.excel.Fila;
import com.prosegur.excel.Hoja;

public class SeccionDefinicionTDAccionesImpl extends SeccionDefinicionTD {

	@Override
	public void crear(GuidedDecisionTable52 tablaDecision, Hoja hoja) {
		for(ActionCol52 columna: tablaDecision.getActionCols()) {
			if (columna != null && columna instanceof ActionSetFieldCol52) {
				ActionSetFieldCol52 colAction = (ActionSetFieldCol52)columna;
				Fila fila = crearCeldaTitulo(hoja, true, colAction.getHeader());
				crearCeldaDato(fila, Boolean.toString(colAction.isHideColumn()),
									 colAction.getBoundName(),
									 colAction.getFactField(),
									 Dato.getTipoDato(colAction.getType()),
									 Dato.getValor(colAction.getDefaultValue()),
									 Boolean.toString(colAction.isUpdate()));
			}					
		}
	}

	@Override
	public void leer(Hoja hoja, GuidedDecisionTable52 tablaDecision) {
		List<ActionCol52> acciones = procesarSeccion(hoja, filaSeccion.numeroDeFila(), fila -> {
			TipoDato tipoDato = TipoDato.valueOf(getString(fila, 5));
			ActionSetFieldCol52 accion = new ActionSetFieldCol52();
			accion.setHeader(getString(fila, 1));
			accion.setWidth(-1);
			accion.setHideColumn(getBooleano(fila, 2));
			accion.setBoundName(getString(fila, 3));
			accion.setFactField(getString(fila, 4));
			accion.setType(tipoDato.getNombre());
			String valorDefecto = getString(fila, 6);
			if (!valorDefecto.equals("")) {
				accion.setDefaultValue(tipoDato.nuevoCellValue(valorDefecto));
			}	
			accion.setUpdate(getBooleano(fila, 7));
			return accion;
		});	
		
		tablaDecision.setActionCols(acciones);
	}

}
