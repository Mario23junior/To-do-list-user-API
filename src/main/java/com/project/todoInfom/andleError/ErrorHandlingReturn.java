package com.project.todoInfom.andleError;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.project.todoInfom.exceptions.IntegrityViolationOnlyException;
import com.project.todoInfom.exceptions.ObjectNotFoundExecution;

@ControllerAdvice
public class ErrorHandlingReturn {
  
	@ExceptionHandler(ObjectNotFoundExecution.class)
	public ResponseEntity<modelErroCustom> objectNotFundReturn(ObjectNotFoundExecution ex, HttpServletRequest request) {
		modelErroCustom erro = new modelErroCustom(
				LocalDateTime.now(),
				HttpStatus.NOT_FOUND.value(),
				ex.getMessage(),
				request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(IntegrityViolationOnlyException.class)
	public ResponseEntity<modelErroCustom> dataIntegrationVioletion(DataIntegrityViolationException dataIntegrityViolationException, HttpServletRequest request) {
		modelErroCustom erro = new modelErroCustom(
				LocalDateTime.now(),
				HttpStatus.BAD_REQUEST.value(),
				dataIntegrityViolationException.getMessage(),
				request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
}
