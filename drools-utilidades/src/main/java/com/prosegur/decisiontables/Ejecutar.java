package com.prosegur.decisiontables;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Paths;

import com.prosegur.excel.TipoArchivo;

public class Ejecutar {

	public static void main(String[] args) {
		System.out.println("inicio");
		
		 TablaDecision tablaDecision =
		 TablaDecision.iniciaDesdeGDST("p_iss.gdst");
		 tablaDecision.crearExcel(Paths.get("C:/Priscila/DESCARGAS/prosegur"), TipoArchivo.XLS);
		 System.out.println("Gerado arquivo XLS");
		
//		TablaDecision tablaDecision2 = TablaDecision
//				.iniciaDesdeExcel(Paths.get("C:/Priscila/DESCARGAS/prosegur/v_svida_fixo.xls"));
//		tablaDecision2.creaGdst(tablaDecision2.getFuenteGDST(), "C:/Priscila/DESCARGAS/prosegur/v_svida_fixo.gdst");
//		System.out.println("Gerado arquivo gdst");
		System.out.println("Fim");
	}
}
