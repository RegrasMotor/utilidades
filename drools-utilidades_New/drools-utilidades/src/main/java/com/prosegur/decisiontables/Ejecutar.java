package com.prosegur.decisiontables;

import java.nio.file.Paths;

import com.prosegur.excel.TipoArchivo;

public class Ejecutar {

	public static void main(String[] args) {
		System.out.println("Inicio");
		/*
		TablaDecision tablaDecision = TablaDecision.iniciaDesdeGDST("v_examen_psico.gdst");
		tablaDecision.crearExcel(Paths.get("C:/Priscila/DESCARGAS/prosegur"), TipoArchivo.XLS);
		
		TablaDecision tablaDecision1 = TablaDecision.iniciaDesdeGDST("v_examenes.gdst");
		tablaDecision1.crearExcel(Paths.get("C:/Priscila/DESCARGAS/prosegur"), TipoArchivo.XLS);
		 */
		TablaDecision tablaDecision2 = TablaDecision.iniciaDesdeGDST("p_indenizao.gdst");
		tablaDecision2.crearExcel(Paths.get("C:/Priscila/DESCARGAS/prosegur"), TipoArchivo.XLS);
		/*
		TablaDecision tablaDecision = TablaDecision.iniciaDesdeGDST("p_iss.gdst");
		tablaDecision.crearExcel(Paths.get("C:/Priscila/DESCARGAS/prosegur"), TipoArchivo.XLS);
		*/
		/*
		TablaDecision tablaDecision2 = TablaDecision.iniciaDesdeExcel(Paths.get("C:/Priscila/DESCARGAS/prosegur/p_iss.xls"));
		System.out.println(tablaDecision2.getFuenteGDST());*/
		
		System.out.println("Terminado");
	}
}
