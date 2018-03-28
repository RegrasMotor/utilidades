package com.prosegur.excel;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.IndexedColors;

public class EstiloBordeCelda {

	private BorderStyle estilo;
	private IndexedColors color;
	
	public EstiloBordeCelda(BorderStyle estilo, IndexedColors color) {
		this.estilo = estilo==null?BorderStyle.THIN:estilo;
		this.color = color==null?IndexedColors.GREY_50_PERCENT:color;
	}
	
	public static final EstiloBordeCelda nuevo(BorderStyle estilo, IndexedColors color) {
		return new EstiloBordeCelda(estilo, color);
	}
	
	public static final EstiloBordeCelda nuevo(IndexedColors color) {
		return new EstiloBordeCelda(null, color);
	}	
	
	public static final EstiloBordeCelda nuevo() {
		return EstiloBordeCelda.nuevo(null);
	}		

	public BorderStyle getEstilo() {
		return estilo;
	}

	public EstiloBordeCelda setEstilo(BorderStyle estilo) {
		this.estilo = estilo;
		return this;
	}

	public IndexedColors getColor() {
		return color;
	}

	public short getColorIndex() {
		return color.getIndex();
	}
	
	public EstiloBordeCelda setColor(IndexedColors color) {
		this.color = color;
		return this;
	}

}
