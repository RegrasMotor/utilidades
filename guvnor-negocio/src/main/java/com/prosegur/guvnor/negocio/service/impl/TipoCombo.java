package com.prosegur.guvnor.negocio.service.impl;

public enum TipoCombo {
	paquete(ComboPaquete.class),
	tipoAsset(ComboTipoAsset.class),
	asset(ComboAsset.class);
	
	private Class<? extends Combo> clase;

	private TipoCombo(Class<? extends Combo> clase) {
		this.clase = clase;
	}

	public Class<?> getClase() {
		return clase;
	}
}
