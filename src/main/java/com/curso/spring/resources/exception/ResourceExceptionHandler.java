package com.curso.spring.resources.exception;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.curso.spring.services.exceptions.AuthorizationException;
import com.curso.spring.services.exceptions.DatabaseException;
import com.curso.spring.services.exceptions.ResourceBadRequestException;
import com.curso.spring.services.exceptions.ResourceNotFoundException;

@RestControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> notFound(ResourceNotFoundException ex, HttpServletRequest request){
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError standardError = new StandardError(Instant.now(), status.value(), error, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(standardError);
	}
	
	@ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request){
        String error = "Database error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(standardError);
    }

	@ExceptionHandler(ResourceBadRequestException.class)
    public ResponseEntity<StandardError> badRequest(ResourceBadRequestException e, HttpServletRequest request){
        String error = "Bad request";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(standardError);
    }
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	 public ResponseEntity<StandardError> badRequest(MethodArgumentNotValidException e, HttpServletRequest request){
        String error = "Bad request";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ValidationError validatioError = new ValidationError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        
        for(FieldError x: e.getBindingResult().getFieldErrors())
        	validatioError.addError(x.getField(), x.getDefaultMessage());
        	
        
        return ResponseEntity.status(status).body(validatioError);
    }
	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> authorization(AuthorizationException ex, HttpServletRequest request){
        String error = "Forbidden!";
        HttpStatus status = HttpStatus.FORBIDDEN;
        StandardError standardError = new StandardError(Instant.now(), status.value(), error, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(standardError);
	}
}
