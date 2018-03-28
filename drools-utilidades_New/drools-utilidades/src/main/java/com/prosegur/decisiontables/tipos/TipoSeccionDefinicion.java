package com.prosegur.decisiontables.tipos;

import java.util.Arrays;

import org.drools.ide.common.client.modeldriven.dt52.GuidedDecisionTable52;

import com.prosegur.decisiontables.hoja.SeccionDefinicionTD;
import com.prosegur.decisiontables.hoja.SeccionDefinicionTDAccionesImpl;
import com.prosegur.decisiontables.hoja.SeccionDefinicionTDAtributosImpl;
import com.prosegur.decisiontables.hoja.SeccionDefinicionTDCondicionesImpl;
import com.prosegur.decisiontables.hoja.SeccionDefinicionTDNombreTablaImpl;
import com.prosegur.excel.Hoja;

public enum TipoSeccionDefinicion {
	NOMBRE_TABLA("TABLE NAME", new SeccionDefinicionTDNombreTablaImpl(), null),
	ATRIBUTOS("ATTRIBUTES", new SeccionDefinicionTDAtributosImpl(), new TipoColumnaDefinicion[] {
																	TipoColumnaDefinicion.ENABLE, 
																	TipoColumnaDefinicion.NAME, 
																	TipoColumnaDefinicion.HIDE_COLUMN, 
																	TipoColumnaDefinicion.REVERSE_ORDER, 
																	TipoColumnaDefinicion.USE_ROW_NUMBER, 
																	TipoColumnaDefinicion.DEFAULT_VALUE}),
	CONDICIONES("CONDITIONS", new SeccionDefinicionTDCondicionesImpl(), new TipoColumnaDefinicion[] {
																		TipoColumnaDefinicion.ENABLE, 
																		TipoColumnaDefinicion.NAME, 
																		TipoColumnaDefinicion.HIDE_COLUMN, 
																		TipoColumnaDefinicion.NEGATED, 
																		TipoColumnaDefinicion.FACT_TYPE, 
																		TipoColumnaDefinicion.BOUND_NAME, 
																		TipoColumnaDefinicion.FACT_FIELD, 
																		TipoColumnaDefinicion.DATA_TYPE, 
																		TipoColumnaDefinicion.OPERATOR, 
																		TipoColumnaDefinicion.DEFAULT_VALUE}),
	ACCIONES("ACTIONS", new SeccionDefinicionTDAccionesImpl(), new TipoColumnaDefinicion[] {
															   TipoColumnaDefinicion.ENABLE, 
															   TipoColumnaDefinicion.NAME, 
															   TipoColumnaDefinicion.HIDE_COLUMN, 
															   TipoColumnaDefinicion.BOUND_NAME, 
															   TipoColumnaDefinicion.FACT_FIELD, 
															   TipoColumnaDefinicion.DATA_TYPE, 
															   TipoColumnaDefinicion.DEFAULT_VALUE, 
															   TipoColumnaDefinicion.UPDATE});

	private String nombre;
	private SeccionDefinicionTD seccionDefinicionTD;
	private TipoColumnaDefinicion[] columnas;

	private TipoSeccionDefinicion(String nombre, SeccionDefinicionTD seccionDefinicionTD, TipoColumnaDefinicion[] columnas) {
		this.nombre = nombre;
		this.seccionDefinicionTD = seccionDefinicionTD;
		this.columnas = columnas;
	}

	public String getNombre() {
		return nombre;
	}
	
	public TipoColumnaDefinicion[] getColumnas() {
		return columnas;
	}
	
	public String[] getNombresColumnas() {
		if (columnas == null) {
			return null;
		} else {
			return Arrays.stream(columnas).map(TipoColumnaDefinicion::getNombre).toArray(String[]::new);
		}
	}	
	
	public void crear(GuidedDecisionTable52 tablaDecision, Hoja hoja) {
		seccionDefinicionTD.crearFilaCabeceraSeccion(hoja, nombre, getNombresColumnas());
		seccionDefinicionTD.crear(tablaDecision, hoja);
	}
	
	public void leer(Hoja hoja, GuidedDecisionTable52 tablaDecision) {
		if (seccionDefinicionTD.buscarCabeceraSeccion(hoja, nombre)) {
			seccionDefinicionTD.leer(hoja, tablaDecision);
		}
	}	
	
}
