package com.curso.spring.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.curso.spring.models.dto.ClienteDTO;
import com.curso.spring.models.entities.Cliente;
import com.curso.spring.repositories.ClienteRepository;
import com.curso.spring.resources.exception.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public void initialize(ClienteUpdate ann) {
	}



	@Override
	public boolean isValid(ClienteDTO objDTO, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
		Long uriId = Long.parseLong(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();	
		
		Cliente aux = clienteRepository.findByEmail(objDTO.getEmail());
		
		if(aux != null && !aux.getId().equals(uriId))
			list.add(new FieldMessage("email", "O email já é existente"));
		
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}