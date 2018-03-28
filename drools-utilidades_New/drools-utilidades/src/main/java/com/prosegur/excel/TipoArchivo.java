package com.prosegur.excel;

public enum TipoArchivo {
	XLS(".xls"),
	XLSX(".xlsx");
	
	private String extension;

	private TipoArchivo(String extension) {
		this.extension = extension;
	}

	public String getExtension() {
		return extension;
	}
}
