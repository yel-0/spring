package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserModel;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // CREATE USER
    public UserModel register(UserModel user) {
        return userRepository.save(user);
    }

    // GET ALL USERS
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }
    
    // Login
    public UserModel login(String username, String password) {

        UserModel user = userRepository.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {

            return user;

        }

        return null;
    }
    
    public UserModel updateUser(UserModel user) {
        return userRepository.save(user);
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }
}