package com.curso.spring.services;

import java.util.List;

import com.curso.spring.models.entities.Categoria;

public interface CategoriaService {
	
	Categoria find(Long id);
	Categoria insert(Categoria obj);
	Categoria atualizar(Long id, Categoria obj);
	List<Categoria> findAll();
	void delete(Long id);
}
