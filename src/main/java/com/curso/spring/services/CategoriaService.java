package com.curso.spring.services;

import com.curso.spring.models.entities.Categoria;

public interface CategoriaService {
	
	Categoria buscar(Long id);
	Categoria inserir(Categoria categoria);

}
