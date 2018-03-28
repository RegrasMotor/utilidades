package com.prosegur.decisiontables.tipos;

import org.drools.ide.common.client.modeldriven.dt52.ActionSetFieldCol52;
import org.drools.ide.common.client.modeldriven.dt52.AttributeCol52;
import org.drools.ide.common.client.modeldriven.dt52.BaseColumn;
import org.drools.ide.common.client.modeldriven.dt52.ConditionCol52;
import org.drools.ide.common.client.modeldriven.dt52.DescriptionCol52;
import org.drools.ide.common.client.modeldriven.dt52.GuidedDecisionTable52;
import org.drools.ide.common.client.modeldriven.dt52.RowNumberCol52;

import com.prosegur.excel.EstiloCelda;

public enum TipoColumna {
	  
	ROWNUMBER("rowNumber", TipoBaseColumna.ROWNUMBER, TipoDato.INTEGER),
	DESCRIPTION("description", TipoBaseColumna.DESCRIPTION, TipoDato.STRING),
	SALIENCE(GuidedDecisionTable52.SALIENCE_ATTR, TipoBaseColumna.ATTRIBUTE, TipoDato.INTEGER),
	LOCK_ON_ACTIVE(GuidedDecisionTable52.LOCK_ON_ACTIVE_ATTR, TipoBaseColumna.ATTRIBUTE, TipoDato.BOOLEAN),
	DATE_EXPIRES(GuidedDecisionTable52.DATE_EXPIRES_ATTR, TipoBaseColumna.ATTRIBUTE, TipoDato.DATE),
	ENABLED(GuidedDecisionTable52.ENABLED_ATTR, TipoBaseColumna.ATTRIBUTE, TipoDato.BOOLEAN),
	NO_LOOP(GuidedDecisionTable52.NO_LOOP_ATTR, TipoBaseColumna.ATTRIBUTE, TipoDato.BOOLEAN),
	DIALECT(GuidedDecisionTable52.DIALECT_ATTR, TipoBaseColumna.ATTRIBUTE, TipoDato.STRING),
	TIMER(GuidedDecisionTable52.TIMER_ATTR, TipoBaseColumna.ATTRIBUTE, TipoDato.STRING),
	RULEFLOW_GROUP(GuidedDecisionTable52.RULEFLOW_GROUP_ATTR, TipoBaseColumna.ATTRIBUTE, TipoDato.STRING),
	DATE_EFFECTIVE(GuidedDecisionTable52.DATE_EFFECTIVE_ATTR, TipoBaseColumna.ATTRIBUTE, TipoDato.DATE),
	AGENDA_GROUP(GuidedDecisionTable52.AGENDA_GROUP_ATTR, TipoBaseColumna.ATTRIBUTE, TipoDato.STRING),
	ACTIVATION_GROUP(GuidedDecisionTable52.ACTIVATION_GROUP_ATTR, TipoBaseColumna.ATTRIBUTE, TipoDato.STRING),
	DURATION(GuidedDecisionTable52.DURATION_ATTR, TipoBaseColumna.ATTRIBUTE, TipoDato.LONG),
	CALENDARS(GuidedDecisionTable52.CALENDARS_ATTR, TipoBaseColumna.ATTRIBUTE, TipoDato.STRING),
	AUTO_FOCUS(GuidedDecisionTable52.AUTO_FOCUS_ATTR, TipoBaseColumna.ATTRIBUTE, TipoDato.BOOLEAN),
	NEGATE(GuidedDecisionTable52.NEGATE_RULE_ATTR, TipoBaseColumna.ATTRIBUTE, TipoDato.STRING),
	CONDITION("condition", TipoBaseColumna.CONDITION, null),
	ACTION("action", TipoBaseColumna.ACTION, null);
	
	private String nombre;
	private TipoBaseColumna tipoBaseColumna;
	private TipoDato tipoDato;

	private TipoColumna(String nombre, TipoBaseColumna tipoBaseColumna, TipoDato tipoDato) {
		this.nombre = nombre;
		this.tipoDato = tipoDato;
		this.tipoBaseColumna = tipoBaseColumna;
	}

	public String getNombre() {
		return nombre;
	}
	
	public TipoBaseColumna getTipoBaseColumna() {
		return tipoBaseColumna;
	}

	public EstiloCelda getEstilo() {
		return tipoBaseColumna.getEstilo();
	}	

	public TipoDato getTipoDato() {
		return tipoDato;
	}
	
	public TipoDato getTipoDatoReal(BaseColumn columna) {
		TipoDato tipoDatoReal = null;
		if (tipoDato == null) {
			if (columna instanceof ConditionCol52) {
				tipoDatoReal = TipoDato.getTipoDato(((ConditionCol52)columna).getFieldType());
			} else if (columna instanceof ActionSetFieldCol52) {
				tipoDatoReal = TipoDato.getTipoDato(((ActionSetFieldCol52)columna).getType());
			}
		} else {
			tipoDatoReal = this.tipoDato;
		}
		return tipoDatoReal;
	}

	public static TipoColumna getTipoColumna(String nombre) {
		for(TipoColumna nombreCol: TipoColumna.values()) {
			if(nombreCol.getNombre().equalsIgnoreCase(nombre)) {
				return nombreCol;
			}
		}
		return null;
	}
	
	public static TipoColumna getTipoColumna(BaseColumn columna) {
		TipoColumna tipoColumna = null;

		if (columna != null) {
			if (columna instanceof RowNumberCol52) {
				tipoColumna = TipoColumna.ROWNUMBER;
			} else if (columna instanceof DescriptionCol52) {
				tipoColumna = TipoColumna.DESCRIPTION;
			} else if (columna instanceof AttributeCol52) {
				String atributo = ((AttributeCol52)columna).getAttribute(); 
				tipoColumna = TipoColumna.getTipoColumna(atributo);
			} else if (columna instanceof ConditionCol52) {
				tipoColumna = TipoColumna.CONDITION;
			} else if (columna instanceof ActionSetFieldCol52) {
				tipoColumna = TipoColumna.ACTION;
			}			
		}
		return tipoColumna;
	}
	
}
