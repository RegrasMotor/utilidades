package com.prosegur.excel;

import org.apache.poi.ss.usermodel.Cell;

public class Celda {

	private Cell celda;
	
	private Celda(Cell celda) {
		this.celda = celda;
	}
	
	static Celda leer(Fila fila, int numCelda) {
		return new Celda(fila.getRow().getCell(numCelda));
	}
	
	@SuppressWarnings("deprecation")
	public String getValor() {
		if (celda != null) {
			if (celda.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				return String.valueOf(celda.getNumericCellValue());
			} else {
				return celda.getStringCellValue();
			}
		} else {
			return "";
		}
	}
	
	@SuppressWarnings("deprecation")
	public boolean estaVacio() {
		return (celda == null || celda.getCellType() == Cell.CELL_TYPE_BLANK);
	}

}
