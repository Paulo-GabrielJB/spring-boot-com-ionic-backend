package com.curso.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.spring.models.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
