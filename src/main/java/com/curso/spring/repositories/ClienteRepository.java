package com.curso.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.spring.models.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
