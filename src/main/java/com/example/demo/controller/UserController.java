package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.UserModel;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // Show register page
    @GetMapping("/register")
    public String registerPage() {

        return "register";
    }

    // Register user
    @PostMapping("/register")
    public String registerUser(UserModel user) {

        System.out.println("Username: " + user.getUsername());
        System.out.println("Password: " + user.getPassword());
        System.out.println("Role: " + user.getRole());

        userService.register(user);

        return "redirect:/users";
    }

    // Show all users
    @GetMapping("/users")
    public String getUsers(Model model) {

        List<UserModel> users = userService.getAllUsers();

        model.addAttribute("users", users);

        return "users";
    }
    
    @PostMapping("/login")
    public String loginUser(
            @ModelAttribute UserModel user,
            HttpSession session,
            Model model) {

        UserModel loginUser =
                userService.login(user.getUsername(), user.getPassword());


        if (loginUser != null) {

            // Create session
            session.setAttribute("user", loginUser);

            return "redirect:/";
        }


        // Login failed
        model.addAttribute("error", "Invalid username or password");

        return "login";
    }
    @PostMapping("/users/update")
    public String updateUser(@ModelAttribute UserModel user) {

        userService.updateUser(user);

        return "redirect:/users";
    }
    
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable long id) {

        userService.deleteUser(id);

        return "redirect:/users";
    }
}