package com.example.user.service;

import java.util.List;

import com.example.user.model.User;
import com.example.user.model.UserDto;

public interface userService {
	List<User>getAllUser();
	User getUserById(long id);
	void deleteUserById(long id);
	void createUser(UserDto UserDto);
	void editUser(long id, UserDto userDto);

}
