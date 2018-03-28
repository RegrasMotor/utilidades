package com.prosegur.decisiontables.hoja;

import com.prosegur.decisiontables.tipos.TipoEstilo;
import com.prosegur.excel.Fila;
import com.prosegur.excel.Hoja;

public abstract class SeccionDefinicionTD extends HojaTD {

	protected Fila filaSeccion;
	
	public void crearFilaCabeceraSeccion(Hoja hoja, String nombreSeccion, String[] columnas) {
		filaSeccion = hoja.crearFila().crearCeldas(TipoEstilo.DATO_4.getEstiloCelda(), nombreSeccion);		
		if (columnas != null) {
			hoja.crearFila().crearCeldas(TipoEstilo.TITULO_1.getEstiloCelda(), columnas);
		}
	}
	
	public boolean buscarCabeceraSeccion(Hoja hoja, String nombreSeccion) {
		int numeroFilas = hoja.getNumeroDeFilas();

		for (int i = 0; i < numeroFilas; i++) {
			Fila fila = hoja.leerFila(i);
			if (!fila.estaVacio() && fila.leerCelda(0).getValor().equals(nombreSeccion)) {
				filaSeccion = fila;
				return true;
			}
		}
		return false;
	}
	
	protected Fila crearCeldaTitulo(Hoja hoja, Boolean habilitado, String nombre) {
		Fila fila = hoja.crearFila();
		fila.crearCeldas(TipoEstilo.DATO_5.getEstiloCelda(), habilitado.toString());
		fila.crearCeldas(TipoEstilo.DATO_1.getEstiloCelda(), nombre);
		return fila;
	}	
	
	protected void crearCeldaDato(Fila fila, String...dato) {
		fila.crearCeldas(TipoEstilo.DATO_3.getEstiloCelda(), dato);
	}
	
}
