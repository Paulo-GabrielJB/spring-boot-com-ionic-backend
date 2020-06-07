package com.curso.spring.models.entities.enums;

public enum TipoCliente {
	
	PESSOA_FISICA(0,  "Pessoa  Física"),
	PESSOA_JURIDICA(1,  "Pessoa Juridica");
	
	private int codigo;
	private String descricao;
	
	private TipoCliente(int codigo, String descricao) {
		this.codigo =  codigo;
		this.descricao = descricao;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoCliente toEnum(Integer codigo) {
		if(codigo == null)
			return null;
		
		for(TipoCliente t: TipoCliente.values())
			if(codigo.equals(t.getCodigo()))
				return t;
		
		throw new IllegalArgumentException("Id inválido: " + codigo);
	}

}
