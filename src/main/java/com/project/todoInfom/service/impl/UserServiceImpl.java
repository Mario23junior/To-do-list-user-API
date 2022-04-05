package com.project.todoInfom.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.todoInfom.dto.UserDTO;
import com.project.todoInfom.exceptions.IntegrityViolationOnlyException;
import com.project.todoInfom.exceptions.ObjectNotFoundExecution;
import com.project.todoInfom.model.User;
import com.project.todoInfom.repository.UserRepository;
import com.project.todoInfom.service.UserService;

@Service
public class UserServiceImpl implements UserService {
  
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public User findById(Long id) {
		Optional<User> user = repository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundExecution(" O id "+id+" não foi encontrado pois não "
				+ "existe cadastro associado a este ID"));
 	}
 
	@Override
	public List<User> ListAllBase() {
 		return repository.findAll();
	}

	@Override
	public User creaet(UserDTO obj) {
		ByEmail(obj);
 		return repository.save(mapper.map(obj, User.class));
	}
	
	private void ByEmail(UserDTO userDto) {
		Optional<User> user = repository.findByEmail(userDto.getEmail());
		if(user.isPresent() && !user.get().getId().equals(userDto.getId())) {
		    throw new IntegrityViolationOnlyException("O E-mail "+userDto.getEmail()+" Já existe um cadastro com este e-mail");	
		}
	}

	@Override
	public User update(UserDTO obj) {
		ByEmail(obj);
		return repository.save(mapper.map(obj, User.class));
	}

	@Override
	public void delete(Long id) {
		findById(id);
		repository.deleteById(id);
	}
	
}