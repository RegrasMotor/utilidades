package com.prosegur.guvnor.negocio.dao;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.prosegur.guvnor.negocio.service.AssetsTypes;

@Component
public class AppDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public String getVariable(AssetsTypes tipo, String nombre) {
		SqlAsset sqlAsset = SqlAsset.getSqlAsset(tipo , nombre);
		List<String> lista = (List<String>) jdbcTemplate.queryForList(sqlAsset.getSql(), String.class);
		Collections.sort(lista, String.CASE_INSENSITIVE_ORDER);
		return Arrays.toString(lista.toArray());
	}
	
	public String getVariableMock(AssetsTypes tipo, String nombre) {
		return "['_A=(_A) _ATE', 'A ADM=(A ADM) Agente Administrativo - SISTEMAS', 'A ADM 2=(A ADM 2) Agente Administrativo 2 - SISTEMAS']";
	}	
}
