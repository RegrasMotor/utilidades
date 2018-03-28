package com.prosegur.decisiontables.tipos;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.drools.ide.common.client.modeldriven.dt52.DTCellValue52;
import org.drools.ide.common.client.modeldriven.dt52.DTDataTypes52;

import com.prosegur.decisiontables.Dato;

public enum TipoDato {

	STRING("String", DTDataTypes52.STRING),
	BOOLEAN("Boolean", DTDataTypes52.BOOLEAN),
	DATE("Date", DTDataTypes52.DATE),
	BIGDECIMAL("BigDecimal", DTDataTypes52.NUMERIC_BIGDECIMAL),
	BIGINTEGER("BigInteger", DTDataTypes52.NUMERIC_BIGINTEGER),
	BYTE("Byte", DTDataTypes52.NUMERIC_BYTE),
	DOUBLE("Double", DTDataTypes52.NUMERIC_DOUBLE),
	FLOAT("Float", DTDataTypes52.NUMERIC_FLOAT),
	INTEGER("Integer", DTDataTypes52.NUMERIC_INTEGER),
	LONG("Long", DTDataTypes52.NUMERIC_LONG),
	SHORT("Short", DTDataTypes52.NUMERIC_SHORT);
    
    private String nombre;
    private DTDataTypes52 dataTypeDT;
    
	private TipoDato(String nombre, DTDataTypes52 dataTypeDT) {
		this.nombre = nombre;
		this.dataTypeDT = dataTypeDT;
	}

	public String getNombre() {
		return nombre;
	}

	public DTDataTypes52 getDataTypeDT() {
		return dataTypeDT;
	}
	
    public DTCellValue52 nuevoCellValue(String valor) {
    	DTCellValue52 valorCeldaDT;
    	if (valor == null || valor.isEmpty()) {
    		valorCeldaDT = new DTCellValue52(dataTypeDT, true);
    		valorCeldaDT.clearValues();
        } else {
	    	valorCeldaDT = new DTCellValue52();
	        switch (this) {
	            case BOOLEAN:
	            	valorCeldaDT.setBooleanValue(new Boolean(valor));
	                break;
	            case DATE:
	            	valorCeldaDT.setDateValue(Dato.getFecha(valor));
	                break;
	            case BIGDECIMAL:
	            	valorCeldaDT.setNumericValue(new BigDecimal(valor));
	                break;
	            case BIGINTEGER:
	            	valorCeldaDT.setNumericValue(new BigInteger(valor));
	                break;
	            case BYTE:
	            	valorCeldaDT.setNumericValue(new Byte(valor));
	                break;
	            case DOUBLE:
	            	valorCeldaDT.setNumericValue(new Double(valor));
	                break;
	            case FLOAT:
	            	valorCeldaDT.setNumericValue(new Float(valor));
	                break;
	            case INTEGER:
	            	valorCeldaDT.setNumericValue(new Integer(valor));
	                break;
	            case LONG:
	            	valorCeldaDT.setNumericValue(new Long(valor));
	                break;
	            case SHORT:
	            	valorCeldaDT.setNumericValue(new Short(valor));
	                break;
	            default :
	            	valorCeldaDT.setStringValue(valor);
	        }
        }
        return valorCeldaDT;
    }
	
	public static TipoDato getTipoDato(String nombre) {
		for(TipoDato nombreDato: TipoDato.values()) {
			if(nombreDato.getNombre().equalsIgnoreCase(nombre)) {
				return nombreDato;
			}
		}
		return null;
	}	
    
}
