package com.project.todoInfom.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.project.todoInfom.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
