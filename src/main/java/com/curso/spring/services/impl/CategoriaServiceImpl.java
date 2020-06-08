package com.curso.spring.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.spring.models.entities.Categoria;
import com.curso.spring.repositories.CategoriaRepository;
import com.curso.spring.services.CategoriaService;
import com.curso.spring.services.exceptions.ResourceNotFoundException;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public Categoria buscar(Long id) {
		return categoriaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhuma categoria com o id " + id + " encontrada"));
	}

	@Override
	public Categoria inserir(Categoria categoria) {
		categoria.setId(null);
		return categoriaRepository.save(categoria);
	}
	
}
