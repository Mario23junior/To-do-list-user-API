package com.project.todoInfom.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.todoInfom.dto.UserDTO;
import com.project.todoInfom.model.User;
import com.project.todoInfom.service.UserService;

@RestController
@RequestMapping(value = "/user/")
public class UserResource {

	@Autowired
	private UserService service;

	@Autowired
	private ModelMapper mapper;

	@GetMapping("{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(mapper.map(service.findById(id), UserDTO.class));
	}

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAllData() {
		List<User>  list = service.ListAllBase();
		List<UserDTO> listDto = list.stream().map( l -> mapper.map(l, UserDTO.class)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
 	}

}























