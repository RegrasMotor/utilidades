package com.prosegur.decisiontables.hoja;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.drools.ide.common.client.modeldriven.dt52.AttributeCol52;
import org.drools.ide.common.client.modeldriven.dt52.BaseColumn;
import org.drools.ide.common.client.modeldriven.dt52.DTCellValue52;
import org.drools.ide.common.client.modeldriven.dt52.GuidedDecisionTable52;

import com.prosegur.decisiontables.Dato;
import com.prosegur.decisiontables.tipos.TipoColumna;
import com.prosegur.decisiontables.tipos.TipoEstilo;
import com.prosegur.excel.Fila;
import com.prosegur.excel.Hoja;

public class HojaTDDatosImpl extends HojaTD {

	private static final String COLUMNA_ENABLE = "Enable";
	@Override
	public void crear(GuidedDecisionTable52 tablaDecision, Hoja hoja) {
		//Guarda la cabecera
		Map<Integer, TipoColumna> columnasVisibles = new HashMap<>();
		Fila filaCabecera = hoja.crearFila();
		filaCabecera.crearCeldas(TipoEstilo.TITULO_1.getEstiloCelda(), COLUMNA_ENABLE);
		int i = 0;
		for (BaseColumn columna:tablaDecision.getExpandedColumns()) {
			TipoColumna tipoColuma = TipoColumna.getTipoColumna(columna);
			if (tipoColuma != null && !tipoColuma.equals(TipoColumna.ROWNUMBER)) {
				String nombre = tipoColuma.getTipoDato()==null?columna.getHeader():tipoColuma.getNombre();
				filaCabecera.crearCeldas(TipoEstilo.TITULO_1.getEstiloCelda(), nombre);
				columnasVisibles.put(i, tipoColuma);
			}
			i++;
		}
		
		// Guarda los datos
		for(List<DTCellValue52> datosColumnas:tablaDecision.getData()) {
			Fila filaDatos = hoja.crearFila();
			filaDatos.crearCeldas(TipoEstilo.DATO_5.getEstiloCelda(), Dato.VALOR_TRUE);
			for(int j=0;j<datosColumnas.size();j++) {
				TipoColumna tipoColuma = columnasVisibles.get(j);
				if (tipoColuma != null) {
					filaDatos.crearCeldas(tipoColuma.getEstilo(), Dato.getValor(datosColumnas.get(j)));
				}
			}
		}
		
		// Ajusta el ancho de las columnas automáticamente
		hoja.anchoAutomaticoColumnasModificadas();
	}

	@Override
	public void leer(Hoja hoja, final GuidedDecisionTable52 tablaDecision) {
		AtomicInteger rowCount = new AtomicInteger(1);
		final AtomicInteger colSalienceInverso = new AtomicInteger(0);
		List<List<DTCellValue52>> acciones = procesarSeccion(hoja, -1, fila -> {
			List<DTCellValue52> columnas = new ArrayList<>();
			int numColumna = 0;

			for (BaseColumn columna:tablaDecision.getExpandedColumns()) {
				TipoColumna tipoColuma = TipoColumna.getTipoColumna(columna);
				if (tipoColuma != null) {
					String dato = null;
					if (tipoColuma.equals(TipoColumna.ROWNUMBER)) {
						dato = String.valueOf(rowCount.getAndIncrement());
					} else if (tipoColuma.equals(TipoColumna.SALIENCE)) {
						// Si es salience, comprueba si hay que establecer el número automáticamente.
						AttributeCol52 colSalience = ((AttributeCol52)columna);
						if(colSalience.isUseRowNumber()) {
							if (!colSalience.isReverseOrder()) {
								dato = String.valueOf(rowCount.get()-1);
							} else if(colSalienceInverso.get() == 0) {
								colSalienceInverso.set(numColumna);
							}
						}
					} 
					
					if (dato == null) {
						dato = getString(fila, numColumna);
					}
					columnas.add(tipoColuma.getTipoDatoReal(columna).nuevoCellValue(dato));	
					numColumna++;
				}
			}		
			
			return columnas;
		});	
		
		if(colSalienceInverso.get() > 0 && !acciones.isEmpty()) {
			AtomicInteger salienceRowCount = new AtomicInteger(acciones.size());
			acciones.stream().forEach(lista -> lista.get(colSalienceInverso.get()).setNumericValue(salienceRowCount.getAndDecrement()));
		}
		
		tablaDecision.setData(acciones);
	}

}
