package com.project.todoInfom.service;

import java.util.List;

 import com.project.todoInfom.dto.UserDTO;
 import com.project.todoInfom.model.User;

public interface UserService {
  
	User findById(Long id);
	List<User> ListAllBase();
<<<<<<< HEAD
	User creaet(UserDTO obj);
	User update(UserDTO obj);
	void delete(Long id);
}
=======
 	User  creaet(UserDTO obj);
 }
>>>>>>> b4eac26702150bbeebb6e10580a2f4f7f6d92507
