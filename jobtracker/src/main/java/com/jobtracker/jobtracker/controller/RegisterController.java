package com.jobtracker.jobtracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jobtracker.jobtracker.model.User;
import com.jobtracker.jobtracker.repository.UserRepo;

import jakarta.validation.Valid;

@Controller
public class RegisterController {
    
    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @Autowired
    private UserRepo userrepo;


    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user,
                           BindingResult result) {

        if (result.hasErrors()) {
            return "register"; 
        }

        userrepo.save(user);
        return "index";
    }
}