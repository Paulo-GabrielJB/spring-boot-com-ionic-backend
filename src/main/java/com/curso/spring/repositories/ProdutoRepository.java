package com.curso.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.spring.models.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
