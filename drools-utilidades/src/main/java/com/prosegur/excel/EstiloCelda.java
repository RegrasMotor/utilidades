package com.prosegur.excel;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;

public class EstiloCelda {
	
	private CellStyle estiloCelda;
	private IndexedColors fondoColor;
	private EstiloBordesCelda estiloBordes;
	private IndexedColors fuenteColor;
	private Boolean fuenteNegrita;
	private HorizontalAlignment fuenteCentrado;
	private Short fuenteTamanio;
	
	private EstiloCelda(IndexedColors fondoColor, EstiloBordesCelda estiloBordes, IndexedColors fuenteColor, Boolean fuenteNegrita, HorizontalAlignment fuenteCentrado, Short fuenteTamanio) {
		this.fondoColor = fondoColor;
		this.estiloBordes = estiloBordes;
		this.fuenteColor = fuenteColor;
		this.fuenteNegrita = fuenteNegrita;
		this.fuenteCentrado = fuenteCentrado;
		this.fuenteTamanio = fuenteTamanio;
	}

	public static EstiloCelda nuevo(IndexedColors fondoColor, EstiloBordesCelda estiloBordes, IndexedColors fuenteColor, Boolean fuenteNegrita, HorizontalAlignment fuenteCentrado, Short fuenteTamanio) {
		return new EstiloCelda (fondoColor, estiloBordes, fuenteColor, fuenteNegrita, fuenteCentrado, fuenteTamanio);
	}
	
	CellStyle getEstilo(Workbook libro) {
		if (estiloCelda == null) {
			estiloCelda = libro.createCellStyle();
			if (fondoColor != null) {
				estiloCelda.setFillForegroundColor(fondoColor.getIndex());
				estiloCelda.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			}
			if (estiloBordes != null) {
				estiloBordes.setEstiloBordesCelda(estiloCelda);
			}
			if(fuenteCentrado !=null) {
				estiloCelda.setAlignment(fuenteCentrado);
			}		
			if (fuenteColor != null || estiloBordes != null || fuenteNegrita != null || fuenteTamanio != null) {
				Font fuente = libro.createFont();
				if (fuenteColor != null) {
					fuente.setColor(fuenteColor.getIndex());	
				}
				if (fuenteNegrita != null) {
					fuente.setBold(fuenteNegrita);	
				}
				if (fuenteTamanio != null) {
					fuente.setFontHeightInPoints(fuenteTamanio);
				}		
				estiloCelda.setFont(fuente);
			}
			DataFormat formatoCelda = libro.createDataFormat();
			estiloCelda.setDataFormat(formatoCelda.getFormat("@"));
		}
		return estiloCelda;
	}

}
