package com.curso.spring.models.entities.enums;

public enum Perfil {
	
	ADMIN(0,  "ROLE_ADMIN"),
	CLIENTE(1,  "ROLE_CLIENTE");
	
	private int codigo;
	private String descricao;
	
	private Perfil(int codigo, String descricao) {
		this.codigo =  codigo;
		this.descricao = descricao;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static Perfil toEnum(Integer codigo) {
		if(codigo == null)
			return null;
		
		for(Perfil t: Perfil.values())
			if(codigo.equals(t.getCodigo()))
				return t;
		
		throw new IllegalArgumentException("Id inválido: " + codigo);
	}

}
