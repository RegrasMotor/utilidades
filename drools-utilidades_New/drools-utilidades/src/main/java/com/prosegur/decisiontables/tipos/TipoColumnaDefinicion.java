package com.prosegur.decisiontables.tipos;

public enum TipoColumnaDefinicion {
	NAME("Name"),
	ENABLE("Enable"),
	HIDE_COLUMN("Hide Column"),
	REVERSE_ORDER("Reverse Order"),
	USE_ROW_NUMBER("Use Row Number"), 
	DEFAULT_VALUE("Default value"),
	NEGATED("Negated"),
	FACT_TYPE("Fact Type"),
	BOUND_NAME("Bound Name"),
	FACT_FIELD("Fact Field"),
	DATA_TYPE("Data Type"),
	OPERATOR("Operator"),
	UPDATE("Update");
	
	private String nombre;

	private TipoColumnaDefinicion(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}
}
