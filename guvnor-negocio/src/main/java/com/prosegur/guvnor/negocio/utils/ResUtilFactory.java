package com.prosegur.guvnor.negocio.utils;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prosegur.guvnor.negocio.service.Entorno;

@Component
public class ResUtilFactory {
	
	@Autowired
	private ListableBeanFactory beanFactory;
	
	private Map<String, RestUtil> mapRestUtil;
	
    @PostConstruct
    protected void init() {
    	mapRestUtil = beanFactory.getBeansOfType(RestUtil.class);
    }	
	
	public RestUtil getRestUtil(String entorno) {
		//return Entorno.PRODUCCION.equals(Entorno.getEntorno(entorno)) ? restUtilProduccion : restUtilHomologacion;
		return mapRestUtil.get(Entorno.getEntorno(entorno).getQualifierRestUtil());
	}
}
