package com.curso.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.spring.models.entities.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
