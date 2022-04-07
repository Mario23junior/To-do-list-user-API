package com.project.todoInfom.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;

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

import com.project.todoInfom.dto.UserDTO;
import com.project.todoInfom.exceptions.IntegrityViolationOnlyException;
import com.project.todoInfom.exceptions.ObjectNotFoundExecution;
import com.project.todoInfom.model.User;
import com.project.todoInfom.repository.UserRepository;

@SpringBootTest
public class UserServiceImplTest {

	@InjectMocks
	private UserServiceImpl service;

	@Mock
	private UserRepository repository;

	@Mock
	private ModelMapper mapper;

	private User user;
	private UserDTO userDto;
	private Optional<User> optionalUser;

	Long ID = 1L;
	String NAME = "Orochimaro";
	String EMAIL = "tioOrochimaru@gmai.com";
	String PASSWORD = "123";
	String ErrorReturnMesaage = "O id " + ID + " não foi encontrado pois não " + "existe cadastro associado a este ID";

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startUser();
	}

	@Test
	@DisplayName("Teste de listagem de usuario por id")
	void whenFindByIdThenReturnUserInstance() {
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(optionalUser);
		User response = service.findById(ID);

		assertNotNull(response);

		assertEquals(User.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NAME, response.getNome());
		assertEquals(EMAIL, response.getEmail());

	}

	@Test
	@DisplayName("Teste de falha de listagem de usuario por ID ")
	void WhenFindByIdThenReturnAnObjectNotFundException() {
		Mockito.when(repository.findById(anyLong())).thenThrow(new ObjectNotFoundExecution(ErrorReturnMesaage));

		try {
			service.findById(ID);
		} catch (Exception ex) {
			assertEquals(ObjectNotFoundExecution.class, ex.getClass());
			assertEquals(ErrorReturnMesaage, ex.getMessage());
		}

	}

	@Test
	@DisplayName("Testando listagem de todos os usuarios")
	void findAll() {
		Mockito.when(repository.findAll()).thenReturn(List.of(user));

		List<User> response = service.ListAllBase();

		assertNotNull(response);
		assertEquals(1L, response.size());
		int INDEXInput = 0;
		assertEquals(User.class, response.get(INDEXInput).getClass());

		assertEquals(ID, response.get(INDEXInput).getId());
		assertEquals(NAME, response.get(INDEXInput).getNome());
		assertEquals(EMAIL, response.get(INDEXInput).getEmail());
		assertEquals(PASSWORD, response.get(INDEXInput).getPassword());

	}

	@Test
	@DisplayName("Ao criar um novo usuario retornar sucesso")
	void whenCreateTheReturnSuccess() {
		Mockito.when(repository.save(any())).thenReturn(user);

		User response = service.creaet(userDto);

		assertNotNull(response);
		assertEquals(User.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(EMAIL, response.getEmail());
		assertEquals(NAME, response.getNome());
		assertEquals(PASSWORD, response.getPassword());
	}

	@Test
	@DisplayName("Teste de violação de IDs repetidos retornar exceptions")
	void whenCreateTheReturnAndVioletionData() {
		Mockito.when(repository.findByEmail(anyString())).thenReturn(optionalUser);

		try {
			optionalUser.get().setId(2L);
			service.creaet(userDto);
		} catch (Exception ex) {
			assertEquals(IntegrityViolationOnlyException.class, ex.getClass());
			assertEquals("O E-mail " + userDto.getEmail() + " Já existe um cadastro com este e-mail", ex.getMessage());
		}

	}

	@Test
	@DisplayName("Teste de Atualizaçãoes de informações retornando sucesso")
	void whenUpdateTheReturnSuccess() {
		Mockito.when(repository.save(any())).thenReturn(user);

		User response = service.update(userDto);

		assertNotNull(response);
		assertEquals(User.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(EMAIL, response.getEmail());
		assertEquals(NAME, response.getNome());
		assertEquals(PASSWORD, response.getPassword());
	}
	
	@Test
	@DisplayName("Teste de atualização violada de email epetidos, retornando exceptions")
	void whenUpdateTheReturnAndVioletionData() {
		Mockito.when(repository.findByEmail(anyString())).thenReturn(optionalUser);

		try {
			optionalUser.get().setId(2L);
			service.creaet(userDto);
		} catch (Exception ex) {
			assertEquals(IntegrityViolationOnlyException.class, ex.getClass());
			assertEquals("O E-mail " + userDto.getEmail() + " Já existe um cadastro com este e-mail", ex.getMessage());
		}

	}

	@Test
	@DisplayName("Teste de exclusao retornando sucesso")
	void deleteWithSucesso() {
       Mockito.when(repository.findById(anyLong())).thenReturn(optionalUser);
       
       doNothing().when(repository).deleteById(anyLong());
	   service.delete(ID);
	   Mockito.verify(repository, times(1)).deleteById(anyLong());
       }

	private void startUser() {
		user = new User(ID, NAME, EMAIL, PASSWORD);
		userDto = new UserDTO(ID, NAME, EMAIL, PASSWORD);
		optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));

	}

}
