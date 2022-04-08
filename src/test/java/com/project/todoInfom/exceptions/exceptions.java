package com.project.todoInfom.exceptions;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import com.project.todoInfom.andleError.ErrorHandlingReturn;
import com.project.todoInfom.andleError.modelErroCustom;


@SpringBootTest
public class exceptions {
  
	private ErrorHandlingReturn exceptionsHandler;
	
	@BeforeEach
	void setUp() {
		
	}
	
	@Test
	void WhenObjectNotFoundExcepObjectNotFoundExecution() {
		ResponseEntity<modelErroCustom> response = exceptionsHandler
				.objectNotFundReturn(new ObjectNotFoundExecution("Objeto não foi encontrado"),new MockHttpServletRequest());
		
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(ResponseEntity.class, response.getBody());
		assertEquals("Objeto não foi encontrado", response.getBody().getErro());
		assertEquals(404, response.getBody().getStatus());

		
	}
	
	@Test
	void IntegrityViolationOnlyException() {
		
	}
}
