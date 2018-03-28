package com.prosegur.decisiontables.hoja;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.drools.ide.common.client.modeldriven.brl.CEPWindow;
import org.drools.ide.common.client.modeldriven.dt52.BaseColumn;
import org.drools.ide.common.client.modeldriven.dt52.CompositeColumn;
import org.drools.ide.common.client.modeldriven.dt52.ConditionCol52;
import org.drools.ide.common.client.modeldriven.dt52.GuidedDecisionTable52;
import org.drools.ide.common.client.modeldriven.dt52.Pattern52;

import com.prosegur.decisiontables.Dato;
import com.prosegur.decisiontables.tipos.TipoDato;
import com.prosegur.excel.Fila;
import com.prosegur.excel.Hoja;

public class SeccionDefinicionTDCondicionesImpl extends SeccionDefinicionTD {

	@Override
	public void crear(GuidedDecisionTable52 tablaDecision, Hoja hoja) {
		for (Pattern52 grupoColumna:tablaDecision.getPatterns()) {
			for (ConditionCol52 columna: grupoColumna.getChildColumns()) {
				if (columna != null) {
					Fila fila = crearCeldaTitulo(hoja, true, columna.getHeader());
					crearCeldaDato(fila, Boolean.toString(columna.isHideColumn()),
								 	  	 Boolean.toString(grupoColumna.isNegated()),				 	 
								 	  	 grupoColumna.getFactType(),
								 	  	 grupoColumna.getBoundName(),
								 	  	 columna.getFactField(),
								 	  	 Dato.getTipoDato(columna.getFieldType()),
								 	  	 columna.getOperator(),
								 	  	 Dato.getValor(columna.getDefaultValue()));
				}				
			}
		}	
	}

	@Override
	public void leer(Hoja hoja, GuidedDecisionTable52 tablaDecision) {
		// Crea un mapa con tods los patrones y sus condiciones
		Map<String, Pattern52> patronesMap = new LinkedHashMap<>();
		procesarSeccion(hoja, filaSeccion.numeroDeFila(), fila -> {
			String factType = getString(fila, 4);
			String boundName = getString(fila, 5);
			String identificador = factType + boundName;
			// Crea la condicion
			TipoDato tipoDato = TipoDato.valueOf(getString(fila, 7));
			ConditionCol52 condicion = new ConditionCol52();
			condicion.setHeader(getString(fila, 1));
			condicion.setWidth(-1);
			condicion.setHideColumn(getBooleano(fila, 2));
			condicion.setFactField(getString(fila, 6));
			condicion.setFieldType(tipoDato.getNombre());
			condicion.setOperator(getString(fila, 8));
			String valorDefecto = getString(fila, 9);
			condicion.setDefaultValue(tipoDato.nuevoCellValue(valorDefecto));
			condicion.setParameters(new HashMap<>());
			// Agrupa las condiciones con el mismo patrón.
			Pattern52 patron = patronesMap.get(identificador);
			if(patron == null) {
				patron = new Pattern52();
				patron.setNegated(getBooleano(fila, 3));
				patron.setFactType(factType);
				patron.setBoundName(boundName);	
				CEPWindow window = new CEPWindow();
				window.setOperator("");
				patron.setWindow(window);
				List<ConditionCol52> condiciones = new ArrayList<>();
				condiciones.add(condicion);
				patron.setChildColumns(condiciones);				
				patronesMap.put(identificador, patron);
			} else {
				patron.getChildColumns().add(condicion);
			}			
			return patron;
		});	
		
		// Comvierte el mapa de patrones en lista
		List<CompositeColumn<? extends BaseColumn>> patrones = patronesMap.entrySet().stream().map(x -> x.getValue()).collect(Collectors.toList());
		
		tablaDecision.setConditionPatterns(patrones);
	}

}
