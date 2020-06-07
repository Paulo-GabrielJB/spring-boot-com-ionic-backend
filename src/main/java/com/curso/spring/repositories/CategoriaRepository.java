package com.curso.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.spring.models.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
