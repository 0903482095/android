package com.hoangnt.service;

import com.hoangnt.entity.User;
import com.hoangnt.model.UserDTO;

public interface UserService {
	UserDTO getUser();

	UserDTO getUserByEmail(String email);

	User addUser(UserDTO userDTO);
}
