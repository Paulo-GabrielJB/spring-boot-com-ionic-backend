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
	public Categoria find(Long id) {
		return categoriaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhuma categoria com o id " + id + " encontrada"));
	}

	@Override
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return categoriaRepository.save(obj);
	}

	@Override
	public Categoria atualizar(Long id, Categoria obj) {
		Categoria entity = find(id);
		updateData(obj, entity);
		return categoriaRepository.save(entity);
	}

	private void updateData(Categoria obj, Categoria entity) {
		entity.setNome(obj.getNome());
	}
	
}
