package com.curso.spring.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.curso.spring.models.entities.Produto;

public interface ProdutoService {
	
	Produto find(Long id);
	Page<Produto> search(String nome, List<Long> ids, Integer page, Integer linesPerPage, String orderBy, String direction);
	
}
