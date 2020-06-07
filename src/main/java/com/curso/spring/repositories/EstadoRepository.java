package com.curso.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.spring.models.entities.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

}
