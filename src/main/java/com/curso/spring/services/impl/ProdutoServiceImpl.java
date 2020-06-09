package com.curso.spring.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.stereotype.Service;

import com.curso.spring.models.entities.Categoria;
import com.curso.spring.models.entities.Produto;
import com.curso.spring.repositories.CategoriaRepository;
import com.curso.spring.repositories.ProdutoRepository;
import com.curso.spring.services.ProdutoService;
import com.curso.spring.services.exceptions.ResourceBadRequestException;
import com.curso.spring.services.exceptions.ResourceNotFoundException;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public Produto find(Long id) {
		return produtoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhuma produto com o id " + id + " encontrada"));
	}

	@Override
	public Page<Produto> search(String nome, List<Long> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		try {
			PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
			List<Categoria> categorias = categoriaRepository.findAllById(ids);
			return produtoRepository.findDistinctByNomeIgnoreCaseContainingAndCategoriasIn(nome, categorias, pageRequest);
		} catch(PropertyReferenceException e) {
			throw new ResourceBadRequestException("Valor do parametro informado invalido!");
		}
	}

}
