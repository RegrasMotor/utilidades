package com.prosegur.guvnor.negocio.login;

public enum Usuario {
	admin("$2a$10$xSj9Y.LZCSIgvNecf.7acuW6hXY.tl6Vh1J3paNZA/RdiDBhOQC9a", Rol.ADMIN);

	private String password;
	private Rol rol;

	private Usuario(String password, Rol rol) {
		this.password = password;
		this.rol = rol;
	}

	public String getPassword() {
		return password;
	}
	
	public Rol getRol() {
		return rol;
	}	
	
	public String getRolToString() {
		return rol.name();
	}
}
