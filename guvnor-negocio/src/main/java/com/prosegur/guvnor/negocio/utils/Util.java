package com.prosegur.guvnor.negocio.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Util {
	
	public static List<String> getMatchsPatron(String patron, String texto) {
		Pattern pattern = Pattern.compile(patron);
		List<String> lista = new ArrayList<String>();
		Matcher m = pattern.matcher(texto);
		while (m.find()) {
		    lista.add(m.group());
		}
		return lista;
	}
}
