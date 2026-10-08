package com.example.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.user.model.User;
import com.example.user.model.UserDto;
import com.example.user.repository.UserRepository;


@Service
public class userServiceImpl implements userService{
	
	@Autowired
	private UserRepository userrepo;

	@Override
	public List<User> getAllUser() {
		
		return userrepo.findAll();
	}
	
	@Override
    public User getUserById(long id) {
		
		Optional<User> optional = userrepo.findById(id);
        User user = null;
        if (optional.isPresent())
            user = optional.get();
        else
            throw new RuntimeException(
                "User not found for id : " + id);
        return user;   
     }
	
	@Override
	public void createUser(UserDto UserDto) {
		User user=new User();
		user.setName(UserDto.getName());
		user.setEmail(UserDto.getEmail());
		userrepo.save(user);
	}

	
	@Override
	public void editUser(long id, UserDto userDto) {
		User user=userrepo.findById(id).orElseThrow(() 
				-> new IllegalArgumentException("User not found with id: " + id));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		userrepo.save(user);
	}
	
	@Override
    public void deleteUserById(long id) {
        User user = userrepo.findById(id).orElseThrow(() 
        		-> new IllegalArgumentException("User not found with id: " + id));
        this.userrepo.deleteById(id);
        
    }
	
}
