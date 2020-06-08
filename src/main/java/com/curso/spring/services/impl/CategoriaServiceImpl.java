package com.curso.spring.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.stereotype.Service;

import com.curso.spring.models.entities.Categoria;
import com.curso.spring.repositories.CategoriaRepository;
import com.curso.spring.services.CategoriaService;
import com.curso.spring.services.exceptions.DatabaseException;
import com.curso.spring.services.exceptions.ResourceBadRequestException;
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

	@Override
	public void delete(Long id) {
		try {
			categoriaRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Categoria com o id " + id + " não localizada");
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        } 
	}

	@Override
	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}

	@Override
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		try {
			PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
			
			return categoriaRepository.findAll(pageRequest);
		} catch(PropertyReferenceException e) {
			throw new ResourceBadRequestException("Valor do parametro informado invalido!");
		}
	}
	
}
