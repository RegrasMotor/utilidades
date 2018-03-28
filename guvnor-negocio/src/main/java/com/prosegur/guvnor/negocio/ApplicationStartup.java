package com.prosegur.guvnor.negocio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

	private static Logger logger = LoggerFactory.getLogger(ApplicationStartup.class);
	
	@Value("${aplicacion.entorno}")
	private String entornoAplicacion;

	@Override
	public void onApplicationEvent(final ApplicationReadyEvent event) {
		logger.info("Entorno cargado: {}", entornoAplicacion);
	}

}