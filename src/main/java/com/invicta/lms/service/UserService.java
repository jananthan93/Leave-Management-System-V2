package com.invicta.lms.service;

import java.util.List;

import com.invicta.lms.entity.Role;
import com.invicta.lms.entity.User;

public interface UserService {
	User addUser(User user,Role role);
	List<User> viewAllUser();
	Long deleteUser(Long id);
	User updateUser(Long id,User user,Role role);
	User findUserById(Long id);
}


