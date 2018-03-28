package com.prosegur.excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {

	private Path archivo;
	private Workbook excel;
	
	private Excel(Path archivo, Workbook excel) {
		this.archivo = archivo;
		this.excel = excel;
	}		
	
	public static Excel crear(Path archivo) {
		return new Excel(archivo, crearLibro(archivo));
	}
	
	public static Excel leer(Path archivo) {
		return new Excel(archivo, abrirLibro(archivo));
	}
	
	public Hoja crearHoja(String nombre) {
		return Hoja.crear(excel, nombre);
	}
	
	public Hoja getHoja(String nombre) {
		return Hoja.leer(excel, nombre);
	}	
	
	public void guardar() throws IOException {
		try (FileOutputStream outputStream = new FileOutputStream(archivo.toFile())) {
			excel.write(outputStream);
			excel.close();
		}		
	}
	
	private static Workbook crearLibro(Path archivo) {
		String nombreFichero = archivo.getFileName().toString();
		if (nombreFichero.endsWith(TipoArchivo.XLS.getExtension())) {
			return new HSSFWorkbook();
		} else if (nombreFichero.endsWith(TipoArchivo.XLSX.getExtension())) {
			return new XSSFWorkbook();
		} else {
			throw new InvalidPathException(nombreFichero, String.format("El archivo excel solo puede ser %1$s o %2$s", TipoArchivo.XLS.getExtension(), TipoArchivo.XLSX.getExtension()));
		}
	}		
	
	private static Workbook abrirLibro(Path archivo) {
		try {
			return WorkbookFactory.create(archivo.toFile());
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			throw new InvalidPathException(archivo.getFileName().toString(), "No se ha podido abrir el archivo excel");
		}
	}
	
}
