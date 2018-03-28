package com.prosegur.guvnor.negocio.service;

import java.util.Arrays;
import java.util.List;

public enum QueryXPath {
	PACKAGES_NAME("//package/title/text()[starts-with(., '%s')]"),
	ASSETS_NAME("//asset[metadata/format/text() = '%s']/title/text()");
	
	private String query;
	private List<String> exlusiones;

	private QueryXPath(String query, String...exlusiones) {
		this.query = query;
		this.exlusiones = Arrays.asList(exlusiones);
	}

	public String getQuery(String...params) {
		if (params.length==0) {
			return query;
		} else {
			return String.format(query, (Object[])params);
		}
	}

	public List<String> getExlusiones() {
		return exlusiones;
	}
}
