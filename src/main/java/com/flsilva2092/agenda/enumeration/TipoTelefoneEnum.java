package com.flsilva2092.agenda.enumeration;

/**
 * Enum that classifies the telefone's type.
 * 
 * @author Francisco Silva
 * @since 24/08/2020
 *
 */
public enum TipoTelefoneEnum {

	RESIDENCIAL("RESIDENCIAL"), TRABALHO("TRABALHO"), AMBOS("AMBOS");

	private String value;

	private TipoTelefoneEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	/**
	 * Method that returns the value in the Enum.
	 * 
	 * @author Francisco Silva
	 * @since 24/03/2020
	 * 
	 * @param value
	 * @return a TravelTypeEnum
	 */
	public static TipoTelefoneEnum getEnum(String value) {
		
		for(TipoTelefoneEnum t : values()) {
			if(value.equals(t.getValue())) {
				return t;
			}
		}
		
		throw new RuntimeException("Type not found.");
	}

}
