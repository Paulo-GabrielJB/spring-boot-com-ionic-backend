package com.curso.spring.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.curso.spring.models.entities.Cliente;
import com.curso.spring.repositories.ClienteRepository;
import com.curso.spring.security.UserSS;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Cliente cliente = clienteRepository.findByEmail(email);
		
		if(cliente == null)
			throw new UsernameNotFoundException(email);
		
		return new UserSS(cliente.getId(), cliente.getEmail(), cliente.getSenha(), cliente.getPerfis());
	}

}
