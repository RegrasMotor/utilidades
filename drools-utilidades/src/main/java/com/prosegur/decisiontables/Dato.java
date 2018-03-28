package com.prosegur.decisiontables;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.drools.ide.common.client.modeldriven.dt52.DTCellValue52;

import com.prosegur.decisiontables.tipos.TipoDato;

public abstract class Dato {

	public static final String VALOR_TRUE = Boolean.TRUE.toString();
	private static final SimpleDateFormat FORMATO_FECHA = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
			
	public static String getValor(DTCellValue52 cellValue) {
		String valor = null;
		if (cellValue != null) {
			if(cellValue.getBooleanValue() != null) {
				valor = cellValue.getBooleanValue().toString();
			} else if(cellValue.getDateValue() != null) {
				valor = FORMATO_FECHA.format(cellValue.getDateValue());
			} else if(cellValue.getNumericValue() != null) {
				valor = cellValue.getNumericValue().toString();
			} else if(cellValue.getStringValue() != null) {
				valor = (cellValue.getStringValue().toString());
			}
		}
		return valor==null?"":valor;
	}
	
	public static String getTipoDato(String dataType) {
		return dataType==null?TipoDato.STRING.name():TipoDato.getTipoDato(dataType).name();
	}
	
	public static Date getFecha(String fecha) {
		try {
			return FORMATO_FECHA.parse(fecha);
		} catch (ParseException e) {
			throw new IllegalArgumentException(String.format("No se ha podido convertir con el formato de fecha '%1$s'", FORMATO_FECHA.toPattern()), e);
		}
	}

}
