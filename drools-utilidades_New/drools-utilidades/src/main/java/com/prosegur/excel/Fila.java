package com.prosegur.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

public class Fila {

	private Workbook libro;
	private Hoja hoja;
	private Row fila;
	private int numColumnaCreacion= 0;

	private Fila(Workbook libro, Hoja hoja, Row fila) {
		this.libro = libro;
		this.hoja = hoja;
		this.fila = fila;
	}	
	
	static Fila crear(Workbook libro, Hoja hoja, int numFila) {
		Row fila = hoja.getSheet().createRow(numFila);
		return new Fila(libro, hoja, fila);
	}

	static Fila leer(Workbook libro, Hoja hoja, int numFila) {
		Row fila = hoja.getSheet().getRow(numFila);
		return new Fila(libro, hoja, fila);
	}	
	
	Row getRow() {
		return fila;
	}
	
	public Fila crearCeldas(int numColumna, EstiloCelda estilo, String...datos) {
		int numColumnaActual = numColumna;
		for(String dato:datos) {
			Cell celda = fila.createCell(numColumnaActual);
			celda.setCellValue(dato);
			if (estilo != null) {
				celda.setCellStyle(estilo.getEstilo(libro));
			}	
			hoja.columnaTratada(numColumnaActual);
			numColumnaActual++;
		}
		return this;
	}	
	
	public Fila crearCeldas(EstiloCelda estilo, String...datos) {
		for(String dato:datos) {
			crearCeldas(this.numColumnaCreacion++, estilo, dato);
		}
		return this;
	}
	
	public Fila crearCeldas(int numColumna, String...datos) {
		return crearCeldas(numColumna, null, datos);		
	}	
	
	public Fila crearCeldas(String...datos) {
		return crearCeldas(null, datos);
	}
	
	public Celda leerCelda(int numCelda) {
		return Celda.leer(this, numCelda);
	}
	
	public boolean estaVacio() {
		return fila == null;
	}
	
	public int numeroDeFila() {
		return fila.getRowNum();
	}
}
