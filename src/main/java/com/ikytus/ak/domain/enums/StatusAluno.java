package com.ikytus.ak.domain.enums;

public enum StatusAluno {
	
	ATIVO(1, "Ativo"),
	FORMADO(2, "Formado"),
	INDEFINIDO(3, "Indefinido");
	
	private int cod;
	private String descricao;
	
	private StatusAluno(int cod, String descricao) {
		this.cod = cod;
		this.descricao=descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static StatusAluno toEnum(Integer cod) {
		if(cod==null) {
			return null;
		}
		
		for (StatusAluno x : StatusAluno.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}
