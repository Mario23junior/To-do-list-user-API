package com.project.todoInfom.service;

import java.util.List;

import com.project.todoInfom.model.User;

public interface UserService {
  
	User findById(Long id);
	List<User> ListAllBase();
}
