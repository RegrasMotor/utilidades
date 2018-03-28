package com.prosegur.decisiontables.tipos;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;

import com.prosegur.excel.EstiloBordesCelda;
import com.prosegur.excel.EstiloCelda;

public enum TipoEstilo {
	TITULO_1(EstiloCelda.nuevo(IndexedColors.DARK_TEAL, EstiloBordesCelda.nuevo(), IndexedColors.WHITE, true, HorizontalAlignment.CENTER, null)),
	DATO_1(EstiloCelda.nuevo(IndexedColors.TAN, EstiloBordesCelda.nuevo(), null, null, null, null)),
	DATO_2(EstiloCelda.nuevo(IndexedColors.GREY_25_PERCENT, EstiloBordesCelda.nuevo(), null, null, null, null)),
	DATO_3(EstiloCelda.nuevo(IndexedColors.PALE_BLUE, EstiloBordesCelda.nuevo(), null, null, null, null)),
	DATO_4(EstiloCelda.nuevo(IndexedColors.TEAL, EstiloBordesCelda.nuevo(), IndexedColors.WHITE, null, null, null)),
	DATO_5(EstiloCelda.nuevo(IndexedColors.BROWN, EstiloBordesCelda.nuevo(), IndexedColors.WHITE, null, null, null));

	private EstiloCelda estiloCelda;

	private TipoEstilo(EstiloCelda estiloCelda) {
		this.estiloCelda = estiloCelda;
	}

	public EstiloCelda getEstiloCelda() {
		return estiloCelda;
	}
	
}
