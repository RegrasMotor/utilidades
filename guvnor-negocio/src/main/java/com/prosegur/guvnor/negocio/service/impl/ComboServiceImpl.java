package com.prosegur.guvnor.negocio.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.prosegur.guvnor.negocio.front.Opcion;
import com.prosegur.guvnor.negocio.service.ComboService;
import com.prosegur.guvnor.negocio.utils.ResUtilFactory;
import com.prosegur.guvnor.negocio.utils.RestUtil;

@Service
public class ComboServiceImpl implements ComboService {
	private static Logger logger = LoggerFactory.getLogger(ComboServiceImpl.class);
	
	@Value("${aplicacion.scheduled.cacheCombo.milisegRecargar}")
	private String tiempoRecargarCache;
	
	@Autowired
	private ResUtilFactory resUtilFactory;
	
	private Date fechaUltimoCacheo;
	
	@Override
	public List<Opcion> getCombo(String entorno, String tipo) throws InstantiationException, IllegalAccessException {
		return getCombo(entorno, tipo, null);
	}

	@Override
	public List<Opcion> getCombo(String entorno, String tipo, String id) throws InstantiationException, IllegalAccessException {
		return getCombo(entorno, tipo, id, null);
	}

	@Override
	public List<Opcion> getCombo(String entorno, String tipo, String id, String idAux) throws InstantiationException, IllegalAccessException {
		logger.info("Recupera entorno {} combo {} con id: {}", entorno, tipo, id);

		TipoCombo tipoCombo = TipoCombo.valueOf(tipo);
		RestUtil restUtil = resUtilFactory.getRestUtil(entorno);
		
		Combo combo = (Combo)tipoCombo.getClase().newInstance();
		combo.setCombo(restUtil, entorno, tipo, id, idAux);
		List<Opcion> opciones = combo.getOpciones();

		return opciones;
	}
	
	public String getTiempoRecargarCache() {
		return tiempoRecargarCache;
	}
	
	public Date getFechaUltimoCacheo() {
		return fechaUltimoCacheo;
	}
	
    @Scheduled(fixedDelayString = "${aplicacion.scheduled.cacheCombo.milisegRecargar}", initialDelayString = "${aplicacion.scheduled.cacheCombo.milisegRecargar}")
    private void scheduleFixedRateWithInitialDelayTask() {
    	logger.info("Se ejecuta la recarga de la cache");
    	fechaUltimoCacheo = new Date();
    	for (Combo combo : Combo.getCache().keySet()) {
    		try {
    			combo.recargarCache();
    		} catch (Exception e) {
    			logger.error("Error al recargar la cache del combo: {}", combo.getClave(), e.getMessage()); 
    		}
    	}
        logger.info("Termina la ejecución de la recarga de la cache, proxima ejecución en {} milisegundos", tiempoRecargarCache);
    }
	
    @PostConstruct
    protected void init() {
    	fechaUltimoCacheo = new Date();
    	logger.info("La recarga de la cache se ejecutará en {} milisegundos", tiempoRecargarCache);
    }	
}
