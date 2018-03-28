package com.prosegur.excel;

import java.util.HashSet;
import java.util.Set;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class Hoja {

	private Workbook libro;
	private Sheet hoja;
	private int numFilaCreacion = 0;
	private Set<Integer> columnasTratadas = new HashSet<>();
	
	private Hoja(Workbook libro, Sheet hoja) {
		this.libro = libro;
		this.hoja = hoja;
	}	
	
	static Hoja crear(Workbook libro, String nombre) {
		return new Hoja(libro, libro.createSheet(nombre));
	}

	static Hoja leer(Workbook libro, String nombre) {
		return new Hoja(libro, libro.getSheet(nombre));
	}
	
	Sheet getSheet() {
		return hoja;
	}
	
	void columnaTratada(int numColumna) {
		columnasTratadas.add(numColumna);
	}
	
	public Fila crearFila(int numFila) {
		return Fila.crear(libro, this, numFila);
	}
	
	public Fila crearFila() {
		return crearFila(this.numFilaCreacion++);
	}	
	
	public Fila leerFila(int numFila) {
		return Fila.leer(libro, this, numFila);
	}
	
	public int getNumeroDeFilas() {
		return hoja.getLastRowNum() + 1;
	}
	
	public void anchoAutomaticoColumna(int numColumna) {
		hoja.autoSizeColumn(numColumna);
	}
	
	public void anchoAutomaticoColumnasModificadas() {
		for(Integer numColumna: columnasTratadas) {
			hoja.autoSizeColumn(numColumna);
		}
	}	
	
}
