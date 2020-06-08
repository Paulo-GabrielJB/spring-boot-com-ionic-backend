package com.curso.spring.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.curso.spring.models.dto.ClienteDTO;
import com.curso.spring.models.entities.Cliente;
import com.curso.spring.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> buscar(@PathVariable("id") Long id){
		return ResponseEntity.ok().body(clienteService.find(id));
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> find(){
		List<Cliente> list = clienteService.findAll();
		List<ClienteDTO> listDto = list.stream().map(ClienteDTO::new).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping(value = "/page")
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "lines", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "order", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction){
		Page<Cliente> pagina = clienteService.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> paginaDTO = pagina.map(ClienteDTO::new);
		return ResponseEntity.ok().body(paginaDTO);
	}
	
	@PostMapping
	public ResponseEntity<Cliente> insert(@Valid @RequestBody ClienteDTO objDto){
		Cliente obj = clienteService.insert(new Cliente(objDto));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Cliente> update(@PathVariable Long id, @Valid @RequestBody ClienteDTO objDto){
		return ResponseEntity.ok().body(clienteService.update(id, new Cliente(objDto)));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
