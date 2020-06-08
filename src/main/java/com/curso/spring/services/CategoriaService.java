package com.curso.spring.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.curso.spring.models.entities.Categoria;

public interface CategoriaService {
	
	Categoria find(Long id);
	Categoria insert(Categoria obj);
	Categoria update(Long id, Categoria obj);
	Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction);
	List<Categoria> findAll();
	void delete(Long id);
}
