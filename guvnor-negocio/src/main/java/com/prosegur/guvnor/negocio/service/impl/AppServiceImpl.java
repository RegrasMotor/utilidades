package com.prosegur.guvnor.negocio.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.prosegur.guvnor.negocio.dao.AppDao;
import com.prosegur.guvnor.negocio.service.AppService;
import com.prosegur.guvnor.negocio.service.AssetsTypes;
import com.prosegur.guvnor.negocio.service.Fuente;
import com.prosegur.guvnor.negocio.service.UrlRest;
import com.prosegur.guvnor.negocio.utils.ResUtilFactory;
import com.prosegur.guvnor.negocio.utils.RestUtil;
import com.prosegur.guvnor.negocio.utils.Util;

@Service
public class AppServiceImpl implements AppService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AppServiceImpl.class);
	
	private static final String PATRON_ENUMERATION = "'.+'\\s*:";
	
	@Autowired
	private ResUtilFactory resUtilFactory;
	
	@Autowired
	private AppDao appDao;
	
	@Override
	public String getVariable(String fuente, String entorno, String paquete, String tipo, String nombre) {
		String codigo;
		switch(Fuente.valueOf(fuente)) {
		case BBDD:
			codigo = getVariableBBDD(entorno, paquete, tipo, nombre);
			break;
		case GUVNOR:
			codigo = getVariableGuvnor(entorno, paquete, nombre);
			break;
		default:
			codigo = "";
			break;
		}
		return codigo;
	}
	
	@Override
	public String getVariableBBDD(String entorno, String paquete, String tipo, String nombre) {
		AssetsTypes assetsType = AssetsTypes.getAssetsTypes(tipo);
		String valorBBDD = appDao.getVariableMock(assetsType, nombre);
		return parsearBBDDPorTipo(entorno, assetsType, paquete, nombre, valorBBDD);
	}
	
	@Override
	public String getVariableGuvnor(String entorno, String paquete, String nombre) {
		RestUtil restUtil = resUtilFactory.getRestUtil(entorno);
		ResponseEntity<String> response = restUtil.restCallGETGuvnor(UrlRest.ASSET_SOURCE, paquete, nombre);
		return response.getBody();
	}	
	
	@Override
	public Map<String, Boolean> guardarGuvnor(String entorno, List<String> paquetes, String asset, String codigo) {
		RestUtil restUtil = resUtilFactory.getRestUtil(entorno);
		Map<String, Boolean> resultados = new HashMap<>();
		for (String paquete : paquetes) {
			boolean estado = false;
			try {
				ResponseEntity<String> response = restUtil.restCallPUTGuvnor(codigo, UrlRest.ASSET_SOURCE, paquete, asset);
				estado = HttpStatus.NO_CONTENT.equals(response.getStatusCode());
			} catch (Throwable e) {
				LOGGER.error(e.getMessage());
			}
			resultados.put(paquete, estado);
		}
		return resultados;
	}	
	
	private String parsearBBDDPorTipo(String entorno, AssetsTypes tipo, String paquete, String nombre, String valor) {
		StringBuilder valorParseado = new StringBuilder("");
		switch(tipo) {
		case ENUMERATION:
			String valorGuvnor = getVariableGuvnor(entorno, paquete, nombre);
			List<String> listaClaves = Util.getMatchsPatron(PATRON_ENUMERATION, valorGuvnor);
			boolean primerRegistro = true;
			for (String clave : listaClaves) {
				if (!primerRegistro) {
					valorParseado.append(System.lineSeparator());
				}				
				valorParseado.append(clave).append(" ").append(valor);
				primerRegistro = false;
			}
			break;
		case DRL:
			break;
		case FUNCTION:
			break;
		case GDST:
			break;
		case JAR:
			break;
		case PACKAGE:
			break;
		case SCENARIO:
			break;
		}
		return valorParseado.toString();
	}
	
}
