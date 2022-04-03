package com.project.todoInfom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.todoInfom.model.User;
import com.project.todoInfom.service.UserService;

@RestController
@RequestMapping(value = "/user/")
public class UserResource {
   
	@Autowired
	private UserService service;
	
	@GetMapping("{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){
		return ResponseEntity.ok().body(service.findById(id));
	}
	
}
