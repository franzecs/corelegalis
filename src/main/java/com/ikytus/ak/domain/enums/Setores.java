package com.ikytus.ak.domain.enums;

public enum Setores {
	
	ATENDIMENTOACADEMICO(1, "Aluno"),
	FINANCEIRO(2, "Coodenador"),
	COORDENACAO(3,"Funcionário"),
	DIRECAO(4,"Funcionário"),
	LABORATORIOS(5,"Funcionário"),
	DP(6,"Funcionário"),
	GERENCIAUNIDADE(7,"Funcionário"),
	BIBLIOTECA(8, "Professor");
		
	private int cod;
	private String descricao;
	
	private Setores(int cod, String descricao) {
		this.cod = cod;
		this.descricao=descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Setores toEnum(Integer cod) {
		if(cod==null) {
			return null;
		}
		
		for (Setores x : Setores.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}
