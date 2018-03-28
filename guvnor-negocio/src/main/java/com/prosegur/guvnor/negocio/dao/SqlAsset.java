package com.prosegur.guvnor.negocio.dao;

import com.prosegur.guvnor.negocio.service.AssetsTypes;

public enum SqlAsset {
	TIPO_PUESTO(AssetsTypes.ENUMERATION, "TipoPuesto", "SELECT '''' || TRIM(T.COD_TIPO_PUESTO || '=(' || T.COD_TIPO_PUESTO || ') ' || T.DES_TIPO_PUESTO) || '''' DES_TEXTO FROM COPR_TTIPO_PUESTO T");
	
	private AssetsTypes assetTypes;
	private String nombre;
	private String sql;
	
	private SqlAsset(AssetsTypes assetTypes, String nombre, String sql) {
		this.assetTypes = assetTypes;
		this.nombre = nombre;
		this.sql = sql;
	}

	public AssetsTypes getAssetTypes() {
		return assetTypes;
	}

	public String getNombre() {
		return nombre;
	}

	public String getSql() {
		return sql;
	}
	
	public static SqlAsset getSqlAsset(AssetsTypes assetTypes, String nombre) {
		for (SqlAsset sqlAsset : SqlAsset.values()) {
			if (sqlAsset.getAssetTypes().equals(assetTypes) && sqlAsset.getNombre().equals(nombre)) {
				return sqlAsset;
			}
		}
		return null;
	}

	public static boolean existeNombre(String tipo, String nombre) {
		for (SqlAsset sqlAsset : SqlAsset.values()) {
			if (sqlAsset.assetTypes.getNombre().equals(tipo) && sqlAsset.getNombre().equals(nombre)) {
				return true;
			}
		}
		return false;
	}
}
