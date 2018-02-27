package com.ikytus.ak.domain.enums;

public enum Semestres {
	
	QUINTO(1, "5º"),
	SEXTO(2, "6º"),
	SETIMO(3, "7º"),
	OITAVO(4,"8º"),
	NONO(5,"9º"),
	DECIMO(6,"10º");
	
	private int cod;
	private String descricao;
	
	private Semestres(int cod, String descricao) {
		this.cod = cod;
		this.descricao=descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Semestres toEnum(Integer cod) {
		if(cod==null) {
			return null;
		}
		
		for (Semestres x : Semestres.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}
