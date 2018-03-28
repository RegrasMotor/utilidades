package com.prosegur.excel;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;

public class EstiloBordesCelda {

	private Map<TipoBordeCelda, EstiloBordeCelda> bordesCelda = new HashMap<>();
	
	public EstiloBordesCelda(BorderStyle estiloIzquierda, IndexedColors colorIzquierda, 
							 BorderStyle estiloDerecha, IndexedColors colorDerecha, 
							 BorderStyle estiloArriba, IndexedColors colorArriba, 
							 BorderStyle estiloAbajo, IndexedColors colorAbajo) {
		bordesCelda.put(TipoBordeCelda.IZQUIERDA, EstiloBordeCelda.nuevo(estiloIzquierda, colorIzquierda));
		bordesCelda.put(TipoBordeCelda.DERECHA, EstiloBordeCelda.nuevo(estiloDerecha, colorDerecha));
		bordesCelda.put(TipoBordeCelda.ARRIBA, EstiloBordeCelda.nuevo(estiloArriba, colorArriba));
		bordesCelda.put(TipoBordeCelda.ABAJO, EstiloBordeCelda.nuevo(estiloAbajo, colorAbajo));
	}
	
	public static final EstiloBordesCelda nuevo(BorderStyle estiloIzquierda, IndexedColors colorIzquierda, 
								   				BorderStyle estiloDerecha, IndexedColors colorDerecha, 
							   					BorderStyle estiloArriba, IndexedColors colorArriba,
							   					BorderStyle estiloAbajo, IndexedColors colorAbajo) {
		return new EstiloBordesCelda(estiloIzquierda, colorIzquierda, estiloDerecha, colorDerecha, estiloArriba, colorArriba, estiloAbajo, colorAbajo);
	}
	
	public static final EstiloBordesCelda nuevo(IndexedColors colorIzquierda, IndexedColors colorDerecha, IndexedColors colorArriba, IndexedColors colorAbajo) {
		return new EstiloBordesCelda(null, colorIzquierda, null, colorDerecha, null, colorArriba, null, colorAbajo);
	}	
	
	public static final EstiloBordesCelda nuevo(BorderStyle estilo, IndexedColors color) {
		return new EstiloBordesCelda(estilo, color, estilo, color, estilo, color, estilo, color);
	}
	
	public static final EstiloBordesCelda nuevo(IndexedColors color) {
		return EstiloBordesCelda.nuevo(null, color);
	}	
	
	public static final EstiloBordesCelda nuevo() {
		return EstiloBordesCelda.nuevo(null, null);
	}		
	
	public EstiloBordeCelda getEstiloBorde(TipoBordeCelda tipo) {
		return bordesCelda.get(tipo);
	}
	
	public void setEstiloBordesCelda(CellStyle estiloCelda) {
		setEstiloBordeCelda(TipoBordeCelda.IZQUIERDA,(estilo, color) -> {
			estiloCelda.setBorderLeft(estilo);
			estiloCelda.setLeftBorderColor(color);
		});
		
		setEstiloBordeCelda(TipoBordeCelda.DERECHA,(estilo, color) -> {
			estiloCelda.setBorderRight(estilo);
			estiloCelda.setRightBorderColor(color);
		});
		
		setEstiloBordeCelda(TipoBordeCelda.ARRIBA,(estilo, color) -> {
			estiloCelda.setBorderTop(estilo);
			estiloCelda.setTopBorderColor(color);
		});
		
		setEstiloBordeCelda(TipoBordeCelda.ABAJO,(estilo, color) -> {
			estiloCelda.setBorderBottom(estilo);
			estiloCelda.setBottomBorderColor(color);
		});		
	}
	
	private void setEstiloBordeCelda(TipoBordeCelda tipo, BiConsumer<BorderStyle, Short> funcion) {
		EstiloBordeCelda estiloBorde = bordesCelda.get(tipo);
		funcion.accept(estiloBorde.getEstilo(), estiloBorde.getColorIndex());
	}
}
