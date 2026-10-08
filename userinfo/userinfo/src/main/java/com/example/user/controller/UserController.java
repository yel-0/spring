package com.example.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;

import com.example.user.model.User;
import com.example.user.model.UserDto;
import com.example.user.service.userService;
import jakarta.validation.Valid;

@Controller
public class UserController {
	@Autowired
    private userService userservice;
    
    
    @GetMapping("/")
    public String showUserList(Model model) {
        List<User> users = userservice.getAllUser();
        model.addAttribute("users", users);
        return "users/index";
        
    }
    
        
    @GetMapping("/create")
	public String showCreatePage(Model model) {
    	UserDto userDto = new UserDto(); // Initialize the object
        model.addAttribute("userDto", userDto); // Add it to the model
        return "users/createUserPage"; // Return the view
	}
    
    @PostMapping("/create")
    public String createUser(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult result) {
    	if (result.hasErrors()) {
            return "users/createUserPage"; // Return to the form if there are validation errors
        }

        // Save user data
        userservice.createUser(userDto);
        return "redirect:/"; // Redirect to the users list page
    }
    
    @GetMapping("/edit/{id}")//Method for Displaying the Edit Page
    public String showEditPage(Model model, @PathVariable long id) {
        User user = userservice.getUserById(id);
        if (user == null) {
            return "redirect:/users";
        }

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());

        model.addAttribute("userDto", userDto);
        return "users/updateUserPage";
    }

    
    
    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable long id, @Valid @ModelAttribute("userDto") UserDto userDto,
                           BindingResult result, Model model) {
        if (result.hasErrors()) {
            // If there are validation errors, re-display the form
            return "users/updateUserPage";
        }

        // If no errors, proceed with saving the data
        userservice.editUser(id, userDto);
        return "redirect:/";
    }


    
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable(value = "id") long id) {
        this.userservice.deleteUserById(id);        
        return "redirect:/";
    }
    
}