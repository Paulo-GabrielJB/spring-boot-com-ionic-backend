package com.curso.spring.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.curso.spring.models.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	@Transactional(readOnly = true)
	Cliente findByEmail(String email);
	@Transactional(readOnly = true)
	Cliente findByCpfOuCnpj(String cpfOuCnpj);
	
}
