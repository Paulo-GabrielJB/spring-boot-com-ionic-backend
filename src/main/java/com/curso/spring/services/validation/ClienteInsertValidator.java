package com.curso.spring.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.curso.spring.models.dto.ClienteNewDTO;
import com.curso.spring.models.entities.Cliente;
import com.curso.spring.models.entities.enums.TipoCliente;
import com.curso.spring.repositories.ClienteRepository;
import com.curso.spring.resources.exception.FieldMessage;
import com.curso.spring.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	@Override
	public void initialize(ClienteInsert ann) {
	}



	@Override
	public boolean isValid(ClienteNewDTO objDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if(objDTO.getTipoCliente() == null) 
			list.add(new FieldMessage("tipoCliente", "TipoCliente não pode ser nulo"));
		
		
		if(objDTO.getTipoCliente() != null && objDTO.getTipoCliente().equals(TipoCliente.PESSOA_FISICA.getCodigo()) && !BR.isValidCPF(objDTO.getCpfOuCnpj()))
			list.add(new FieldMessage("cpfOuCnpj", "CPF invalido"));
		
		if(objDTO.getTipoCliente() != null && objDTO.getTipoCliente().equals(TipoCliente.PESSOA_JURIDICA.getCodigo()) && !BR.isValidCNPJ(objDTO.getCpfOuCnpj()))
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ invalido"));
		
		Cliente aux = clienteRepository.findByEmail(objDTO.getEmail());
		
		if(aux != null)
			list.add(new FieldMessage("email", "O email já é existente"));
		
		aux = clienteRepository.findByCpfOuCnpj(objDTO.getCpfOuCnpj());
		
		if(aux != null)
			list.add(new FieldMessage("cpfOuCnpj", "O CPF ou CNPJ é existente"));
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}