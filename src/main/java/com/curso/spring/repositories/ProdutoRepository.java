package com.curso.spring.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.curso.spring.models.entities.Categoria;
import com.curso.spring.models.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

//	@Query("SELECT DISTINCT obj FROM Produto obj "
//			+ "INNER JOIN obj.categorias cat "
//			+ "WHERE UPPER(obj.nome) LIKE %:nome% AND "
//			+ "cat IN :categorias")
	@Transactional(readOnly = true)
	Page<Produto> findDistinctByNomeIgnoreCaseContainingAndCategoriasIn(String nome, List<Categoria> categorias, Pageable pageRequest);
}
