package com.prosegur.decisiontables.hoja;

import org.drools.ide.common.client.modeldriven.dt52.GuidedDecisionTable52;

import com.prosegur.decisiontables.tipos.TipoEstilo;
import com.prosegur.excel.Hoja;

public class SeccionDefinicionTDNombreTablaImpl extends SeccionDefinicionTD {

	@Override
	public void crear(GuidedDecisionTable52 tablaDecision, Hoja hoja) {
		filaSeccion.crearCeldas(TipoEstilo.DATO_1.getEstiloCelda(), tablaDecision.getTableName());
	}

	@Override
	public void leer(Hoja hoja, GuidedDecisionTable52 tablaDecision) {
		tablaDecision.setTableName(filaSeccion.leerCelda(1).getValor());
	}

}
