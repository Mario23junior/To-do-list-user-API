package com.project.todoInfom.resurces;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.todoInfom.controller.UserResource;
import com.project.todoInfom.dto.UserDTO;
import com.project.todoInfom.model.User;
import com.project.todoInfom.service.UserService;

@SpringBootTest
public class UserControllerTest {

	@InjectMocks
	private UserResource resource;

	@Mock
	private UserService service;

	@Mock
	private ModelMapper mapper;

	Long ID = 1L;
	String NAME = "Orochimaro";
	String EMAIL = "tioOrochimaru@gmai.com";
	String PASSWORD = "123";

	private User user;
	private UserDTO userDto;
	private Optional<User> optionalUser;
	private int INDEX = 0;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startUser();
	}

	@Test
	@DisplayName("Teste de listagem de usuario por id")
	void whenFindFindByIdReturnSucesso() {
		when(service.findById(Mockito.anyLong())).thenReturn(user);
		when(mapper.map(any(), any())).thenReturn(userDto);

		ResponseEntity<UserDTO> response = resource.findById(ID);

		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(UserDTO.class, response.getBody().getClass());

		assertEquals(ID, response.getBody().getId());
		assertEquals(EMAIL, response.getBody().getEmail());
		assertEquals(NAME, response.getBody().getNome());
		assertEquals(PASSWORD, response.getBody().getPassword());
	}

	@Test
	@DisplayName("Teste de listagem de todos os usuarios esperando sucessos")
	void WhenFindAllThenReturnListOfUserDto() {
		when(service.ListAllBase()).thenReturn(List.of(user));
		when(mapper.map(any(), any())).thenReturn(userDto);

		ResponseEntity<List<UserDTO>> response = resource.findAllData();

		assertNotNull(response);
		assertNotNull(response.getBody());

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(ArrayList.class, response.getBody().getClass());
		assertEquals(UserDTO.class, response.getBody().get(INDEX).getClass());

		assertEquals(ID, response.getBody().get(INDEX).getId());
		assertEquals(NAME, response.getBody().get(INDEX).getNome());
		assertEquals(EMAIL, response.getBody().get(INDEX).getEmail());
		assertEquals(PASSWORD, response.getBody().get(INDEX).getPassword());

	}

	@Test
	@DisplayName("Teste de criacao de usuarios")
	void WhenCreateTheReturnSucess() {
		when(service.creaet(any())).thenReturn(user);

		ResponseEntity<UserDTO> response = resource.create(userDto);

		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getHeaders().get("Location"));
	}

	@Test
	@DisplayName("Teste de atualização de software")
	void WhenUpdateThenReturnSucess() {
		when(service.update(userDto)).thenReturn(user);
		when(mapper.map(any(), any())).thenReturn(userDto);

		ResponseEntity<UserDTO> response = resource.update(ID, userDto);

		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(UserDTO.class, response.getBody().getClass());

		assertEquals(ID, response.getBody().getId());
		assertEquals(EMAIL, response.getBody().getEmail());
		assertEquals(NAME, response.getBody().getNome());
	}

	@Test
	@DisplayName("Teste de exclusao de usuario")
	void whenDeleteThenReturnSucess() {
       doNothing().when(service).delete(anyLong());
       
       ResponseEntity<UserDTO> response = resource.delete(ID);
       
       assertNotNull(response);
       assertEquals(ResponseEntity.class, response.getClass());
       verify(service,times(1)).delete(anyLong());
       assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
       

	}
 
	private void startUser() {
		user = new User(ID, NAME, EMAIL, PASSWORD);
		userDto = new UserDTO(ID, NAME, EMAIL, PASSWORD);
	}
}
