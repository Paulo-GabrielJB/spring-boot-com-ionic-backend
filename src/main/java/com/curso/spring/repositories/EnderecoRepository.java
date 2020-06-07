package com.curso.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.spring.models.entities.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
