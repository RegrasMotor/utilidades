package com.prosegur.decisiontables.hoja;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.drools.ide.common.client.modeldriven.dt52.GuidedDecisionTable52;

import com.prosegur.decisiontables.Dato;
import com.prosegur.excel.Fila;
import com.prosegur.excel.Hoja;

public abstract class HojaTD {
	
	public abstract void crear(GuidedDecisionTable52 tablaDecision, Hoja hoja);
	
	public abstract void leer(Hoja hoja, GuidedDecisionTable52 tablaDecision);
	
	protected boolean getBooleano(Fila fila, int numColumna) {
		return Boolean.parseBoolean(fila.leerCelda(numColumna).getValor());
	}
	
	protected String getString(Fila fila, int numColumna) {
		return fila.leerCelda(numColumna).getValor();
	}	
	
	protected <R> List<R> procesarSeccion(Hoja hoja, int numFilaSeccion, Function<Fila,R> funcion) {
		List<R> listaResultados = new ArrayList<>();

		int numeroFilas = hoja.getNumeroDeFilas();
		for (int i = numFilaSeccion+2; i < numeroFilas; i++) {
			Fila fila = hoja.leerFila(i);
			if (fila.estaVacio() || fila.leerCelda(0).estaVacio()) {
				break;
			} else if(getString(fila, 0).equals(Dato.VALOR_TRUE)) {
                R registro = funcion.apply(fila);
                listaResultados.add(registro);
			}
		}			
        return listaResultados;
	}
	
}
