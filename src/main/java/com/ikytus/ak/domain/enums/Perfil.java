package com.ikytus.ak.domain.enums;

public enum Perfil {
	
	ADMIN(1, "ROLE_ADMIN"),
	ALUNO(2, "ROLE_ALUNO"),
	EDITOR(3,"ROLE_EDITOR"),
	USUARIO(4,"ROLE_USUARIO"),
	NPJ(5,"ROLE_NPJ"),
	ADMNPJ(6,"ROLE_ADMNPJ");
	
	private int cod;
	private String descricao;
	
	private Perfil(int cod, String descricao) {
		this.cod = cod;
		this.descricao=descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Perfil toEnum(Integer cod) {
		if(cod==null) {
			return null;
		}
		
		for (Perfil x : Perfil.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}
