package com.curso.spring.services.impl;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.curso.spring.models.entities.Cliente;
import com.curso.spring.models.entities.enums.Perfil;
import com.curso.spring.repositories.ClienteRepository;
import com.curso.spring.security.UserSS;
import com.curso.spring.services.ClienteService;
import com.curso.spring.services.ImageService;
import com.curso.spring.services.S3Service;
import com.curso.spring.services.UserService;
import com.curso.spring.services.exceptions.AuthorizationException;
import com.curso.spring.services.exceptions.DatabaseException;
import com.curso.spring.services.exceptions.ResourceBadRequestException;
import com.curso.spring.services.exceptions.ResourceNotFoundException;

@Service
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private BCryptPasswordEncoder pe;
	@Autowired
	private S3Service s3Service;
	@Autowired
	private ImageService imageService;
	
	@Value("${img.prefix.client.profile}")
	private String prefix;

	@Override
	public Cliente find(Long id) {
		UserSS user = UserService.authenticated();
		if(user == null || !user.hasRole(Perfil.ADMIN) && !user.getId().equals(id))
			throw new AuthorizationException("Acesso negado!");
		return clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente com o id " + id + "não encontrado"));
	}

	@Override
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj.setSenha(pe.encode(obj.getSenha()));
		return clienteRepository.save(obj);
	}

	@Override
	public Cliente update(Long id, Cliente obj) {
		Cliente entity = find(id);
		updateData(obj, entity);
		return clienteRepository.save(entity);
	}

	private void updateData(Cliente obj, Cliente entity) {
		entity.setNome(obj.getNome());
		entity.setEmail(obj.getEmail());
	}

	@Override
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		try {
			PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
			
			return clienteRepository.findAll(pageRequest);
		} catch(PropertyReferenceException e) {
			throw new ResourceBadRequestException("Valor do parametro informado invalido!");
		}
	}

	@Override
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		try {
			clienteRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Cliente com o id " + id + " não localizada");
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        } 
		
	}

	@Override
	public URI uploadProfilePictur(MultipartFile multipartfile) {
		UserSS user = UserService.authenticated();
		if(user == null)
			throw new AuthorizationException("Acesso negado!");
		
		BufferedImage jpgImage = imageService.getJpgImagemFromFile(multipartfile);
		
		Cliente cliente = clienteRepository.findById(user.getId()).orElseThrow(() -> new ResourceNotFoundException("Cliente não localizado"));
		
		String fileName = prefix + cliente.getId() + ".jpg";
		
		
		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
	}

}
