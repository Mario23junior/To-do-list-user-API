package com.project.todoInfom.resurces;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.todoInfom.controller.UserResource;
import com.project.todoInfom.dto.UserDTO;
import com.project.todoInfom.model.User;
import com.project.todoInfom.service.UserService;

@SpringBootTest
public class UserControllerTest {

	@InjectMocks
	private  UserResource resurce;
	
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

	@BeforeEach
	void setUp() {
       MockitoAnnotations.openMocks(this);
       startUser();
	}

	@Test
	void findById() {

	}

	@Test
	void findAll() {

	}

	@Test
	void create() {

	}

	@Test
	void update() {

	}

	@Test
	void delete() {

	}
	
	private void startUser() {
		user = new User(ID, NAME, EMAIL, PASSWORD);
		userDto = new UserDTO(ID, NAME, EMAIL, PASSWORD);
	}
}
