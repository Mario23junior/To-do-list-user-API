package com.project.todoInfom.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.todoInfom.dto.UserDTO;
import com.project.todoInfom.model.User;
import com.project.todoInfom.service.UserService;

@RestController
@RequestMapping(value = "/user/")
public class UserResource {

	private static final String ID = "/{id}";

	@Autowired
	private UserService service;

	@Autowired
	private ModelMapper mapper;

	@GetMapping(ID)
	public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(mapper.map(service.findById(id), UserDTO.class));
	}

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAllData() {
		List<User>  list = service.ListAllBase();
		List<UserDTO> listDto = list.stream().map( l -> mapper.map(l, UserDTO.class)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
 	}
	
	@PostMapping
	public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDto) {
		User newUserDto = service.creaet(userDto);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest().path(ID)
				.buildAndExpand(newUserDto.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = ID)
	public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO userDto) {
	   User newObj = service.update(userDto);
	   return ResponseEntity.ok().body(mapper.map(newObj, UserDTO.class));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<UserDTO> delete(@PathVariable Long id) {
	    service.delete(id);
	    return ResponseEntity.noContent().build();
 		
	}
}



















































