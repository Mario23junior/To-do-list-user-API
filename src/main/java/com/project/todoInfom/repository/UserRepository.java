package com.project.todoInfom.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.todoInfom.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByEmail(String email);

}
