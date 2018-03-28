package com.prosegur.guvnor.negocio.front;

public class Opcion {
	private String valor;
	private String texto;
	
	public Opcion() {
	}

	public Opcion(String valor, String texto) {
		super();
		this.valor = valor;
		this.texto = texto;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	@Override
	public String toString() {
		return "Opcion [valor=" + valor + ", texto=" + texto + "]";
	}
}
