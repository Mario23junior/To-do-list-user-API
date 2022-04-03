package com.project.todoInfom.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.todoInfom.exceptions.ObjectNotFoundExecution;
import com.project.todoInfom.model.User;
import com.project.todoInfom.repository.UserRepository;
import com.project.todoInfom.service.UserService;

@Service
public class UserServiceImpl implements UserService {
  
	@Autowired
	private UserRepository repository;
	
	@Override
	public User findById(Long id) {
		Optional<User> user = repository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundExecution(" O id "+id+" não foi encontrado pois não "
				+ "existe cadastro associado a este ID"));
 	}

}
