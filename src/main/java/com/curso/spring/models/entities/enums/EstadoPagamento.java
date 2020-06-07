package com.curso.spring.models.entities.enums;

public enum EstadoPagamento {
	
	PENDENTE(0,  "Pendente"),
	QUITADO(1,  "Quitado"),
	CANCELADO(2, "Cancelado");
	
	private int codigo;
	private String descricao;
	
	private EstadoPagamento(int codigo, String descricao) {
		this.codigo =  codigo;
		this.descricao = descricao;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static EstadoPagamento toEnum(Integer codigo) {
		if(codigo == null)
			return null;
		
		for(EstadoPagamento t: EstadoPagamento.values())
			if(codigo.equals(t.getCodigo()))
				return t;
		
		throw new IllegalArgumentException("Id inválido: " + codigo);
	}

}
