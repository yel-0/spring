package com.example.demo;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.model.UserModel;

import jakarta.servlet.http.HttpSession;

@Controller
public class HelloController {

	@GetMapping("/")
	public String index(HttpSession session, Model model) {

	    UserModel user = (UserModel) session.getAttribute("user");

	    if (user != null) {
	        model.addAttribute("user", user);
	    }

	    return "firstpage";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {

	    session.invalidate();

	    return "redirect:/";
	}
	@GetMapping("/second")
	public String index1() {
		return "second";
	}
	 // Login page
    @GetMapping("/login")
    public String login() {
        return "login";
    }

   
	   @GetMapping("/employee/{id}")
	    public String employee(@PathVariable int id, Model model) {

	        model.addAttribute("employeeId", id);

	        return "employee";
	    }
}
