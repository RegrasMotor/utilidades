package com.prosegur.guvnor.negocio.service;

import javax.xml.transform.dom.DOMSource;

import org.springframework.http.MediaType;

public enum UrlRest {
	PACKAGES("packages", MediaType.APPLICATION_XML, DOMSource.class),
	ASSETS("packages/%s/assets", MediaType.APPLICATION_XML, DOMSource.class),
	ASSET_SOURCE("packages/%s/assets/%s/source", MediaType.TEXT_PLAIN, String.class);
	
	private String url;
	private MediaType accept;
	private Class<?> clase;

	private UrlRest(String url, MediaType accept, Class<?> clase) {
		this.url = url;
		this.accept = accept;
		this.clase = clase;
	}

	public String getUrl(String...params) {
		if (params.length == 0) {
			return url;
		} else {
			return String.format(url, (Object[])params);
		}
	}

	public MediaType getAccept() {
		return accept;
	}

	public Class<?> getClase() {
		return clase;
	}
}
