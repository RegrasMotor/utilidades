package com.prosegur.decisiontables.tipos;

import com.prosegur.excel.EstiloCelda;

public enum TipoBaseColumna {
	ROWNUMBER(null),
	DESCRIPTION(TipoEstilo.DATO_1),
	ATTRIBUTE(TipoEstilo.DATO_2),
	CONDITION(TipoEstilo.DATO_3),
	ACTION(TipoEstilo.DATO_4);
	
	private EstiloCelda estilo;

	private TipoBaseColumna(TipoEstilo tipoEstilo) {
		if (tipoEstilo != null) {
			this.estilo = tipoEstilo.getEstiloCelda();
		}
	}

	public EstiloCelda getEstilo() {
		return estilo;
	}
	
}
