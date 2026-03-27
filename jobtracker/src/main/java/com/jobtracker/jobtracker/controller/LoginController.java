package com.jobtracker.jobtracker.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jobtracker.jobtracker.model.User;
import com.jobtracker.jobtracker.repository.UserRepo;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserRepo userrepo;

    @GetMapping({"/", "/index"})   
    public String showLogin(Model model) {
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping("/index")
    public String login(@ModelAttribute User user, Model model, HttpSession session) {

        User existingUser = userrepo.findByEmail(user.getEmail());

        if (existingUser != null &&
            existingUser.getPassword().equals(user.getPassword())) {

            session.setAttribute("loggedInUser", existingUser);
            return "redirect:/jobs";

        } else {
            model.addAttribute("error", "Invalid Email or Password");
            return "index";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/index";
    }
}