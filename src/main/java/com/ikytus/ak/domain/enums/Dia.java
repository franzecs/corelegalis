package com.ikytus.ak.domain.enums;

public enum Dia {
	
	SEGUNDA(1, "Segunda-Feira"),
	TERCA(2, "Terça-Feira"),
	QUARTA(3,"Quarta-Feira"),
	QUINTA(4,"Quinta-Feira"),
	SEXTA(5,"Sexta-Feira");
	
	private int cod;
	private String descricao;
	
	private Dia(int cod, String descricao) {
		this.cod = cod;
		this.descricao=descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Dia toEnum(Integer cod) {
		if(cod==null) {
			return null;
		}
		
		for (Dia x : Dia.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}
