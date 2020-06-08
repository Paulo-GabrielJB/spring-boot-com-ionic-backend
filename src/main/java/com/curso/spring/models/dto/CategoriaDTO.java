package com.curso.spring.models.dto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.curso.spring.models.entities.Categoria;

public class CategoriaDTO {

	private Long id;
	@NotEmpty(message = "Preenchimento obrigatorio")
	@Size(min = 5, max = 80, message = "O tamanho do nome deve ser entre 5 e 80 caracteres")
	private String nome;
	
	public CategoriaDTO() {}
	
	public CategoriaDTO(Categoria obj) {
		id = obj.getId();
		nome = obj.getNome();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
