package com.prosegur.decisiontables;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.drools.ide.common.client.modeldriven.dt52.GuidedDecisionTable52;
import org.drools.ide.common.server.util.GuidedDTDRLPersistence;
import org.drools.ide.common.server.util.GuidedDTXMLPersistence;

import com.prosegur.decisiontables.tipos.TipoHojaTD;
import com.prosegur.excel.Excel;
import com.prosegur.excel.TipoArchivo;

public class TablaDecision {

	private GuidedDecisionTable52 tablaDecision;
	
	private TablaDecision(GuidedDecisionTable52 tablaDecision) {
		this.tablaDecision = tablaDecision;
	}
	
	public static TablaDecision iniciaDesdeDT52(GuidedDecisionTable52 tablaDecision) {
		return new TablaDecision(tablaDecision);
	}	
	
	public static TablaDecision iniciaDesdeGDST(Path archivoGDST) {
		String xml = ficheroACadena(archivoGDST);
		GuidedDecisionTable52 tablaDecision = GuidedDTXMLPersistence.getInstance().unmarshal(xml);
		return new TablaDecision(tablaDecision);
	}
	
	public static TablaDecision iniciaDesdeGDST(String classPathArchivoGDST) {
		return iniciaDesdeGDST(recursoAPath(classPathArchivoGDST));
	}	
	
	public static TablaDecision iniciaDesdeExcel(Path archivoExcel) {
		Excel excel = Excel.leer(archivoExcel);
		GuidedDecisionTable52 tablaDecision = new GuidedDecisionTable52();
		tablaDecision.getAuditLog();
		
		// Lee todas las hojas del excel en orden inverso
		for (int i = TipoHojaTD.values().length - 1; i >= 0; --i) {
			TipoHojaTD tipoHojaTD = TipoHojaTD.values()[i];
			tipoHojaTD.leer(excel, tablaDecision);
		}		
		return new TablaDecision(tablaDecision);
	}		
	
	public String getFuenteGDST() {
		return GuidedDTXMLPersistence.getInstance().marshal(tablaDecision);
	}
	
	public String getFuenteDRL() {
		return GuidedDTDRLPersistence.getInstance().marshal(tablaDecision);
	}
	
	public void crearExcel(Path directorio, TipoArchivo tipoArchivo) {
		Path archivoExel = directorio.resolve(tablaDecision.getTableName() + tipoArchivo.getExtension());
		Excel excel = Excel.crear(archivoExel);
		
		// Crea todas las hojas del excel
		for(TipoHojaTD tipoHojaTD:TipoHojaTD.values()) {
			tipoHojaTD.crear(tablaDecision, excel);
		}
		
		// Guarda el excel
		try {
			excel.guardar();
		} catch (IOException e) {
			lanzarError("Error al crear el archivo '" + archivoExel.toString() + "'", e);
		}
	}
	
	public void crearExcel(Path directorio) {
		crearExcel(directorio, TipoArchivo.XLS);
	}
	
	private static String ficheroACadena(Path archivo) {
		String xml = null;
		try {
			xml = new String(Files.readAllBytes(archivo));
		} catch (IOException e) {
			lanzarError("Error al leer el archivo '" + archivo + "'", e);
		}	
		return xml;
	}
	
	private static Path recursoAPath(String recursoClassPath) {
		URI ruta = null;
		try {
			 ruta = Ejecutar.class.getClassLoader().getResource(recursoClassPath).toURI();
		} catch (URISyntaxException e) {
			lanzarError("Error al acceder al recurso '" + recursoClassPath + "'", e);
		}
		return Paths.get(ruta);
	}
	
	private static void lanzarError(String texto, Throwable error) {
		RuntimeException runtimeException = new RuntimeException(texto, error);
		throw runtimeException;
	}	

}
