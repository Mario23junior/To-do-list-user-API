package com.project.todoInfom.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.todoInfom.model.User;

@RestController
@RequestMapping(value = "/user/")
public class UserResource {
   
	@GetMapping("{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){
		return ResponseEntity.ok(new User(1L,"nome","mario@gmail.com","123"));
	}
	
}
