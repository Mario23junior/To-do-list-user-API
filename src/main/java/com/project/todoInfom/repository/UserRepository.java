package com.project.todoInfom.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.todoInfom.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
