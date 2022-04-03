package com.project.todoInfom.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.project.todoInfom.model.User;
import com.project.todoInfom.repository.UserRepository;

@Configuration
@Profile("local")
public class LocalConfig {

	@Autowired
	private UserRepository repository;

	@Bean
	public void startDB() {
		User u1 = new User(null, "naruto", "NarutoUzumaki@gmail.com", "123");
		User u2 = new User(null, "Sasuke", "sasukeDesgraçado@gmail.com", "s948n");

		repository.saveAll(List.of(u1, u2));
	}

}
