package com.prosegur.decisiontables.tipos;

import org.drools.ide.common.client.modeldriven.dt52.GuidedDecisionTable52;

import com.prosegur.decisiontables.hoja.HojaTD;
import com.prosegur.decisiontables.hoja.HojaTDDatosImpl;
import com.prosegur.decisiontables.hoja.HojaTDDefinicionImpl;
import com.prosegur.excel.Excel;

public enum TipoHojaTD {
	DATOS("DATA", new HojaTDDatosImpl()),
	DEFINICION("DEFINITION", new HojaTDDefinicionImpl());
	
	private String nombre;
	private HojaTD hojaTD;
	
	private TipoHojaTD(String nombre, HojaTD hojaTD) {
		this.nombre = nombre;
		this.hojaTD = hojaTD;
	}

	public void crear(GuidedDecisionTable52 tablaDecision, Excel excel) {
		hojaTD.crear(tablaDecision, excel.crearHoja(nombre));
	}
	
	public void leer(Excel excel, GuidedDecisionTable52 tablaDecision) {
		hojaTD.leer(excel.getHoja(nombre), tablaDecision);
	}	
	
}
