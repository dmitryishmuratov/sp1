package com.burst.sp1.controller;

import com.burst.sp1.model.User;
import com.burst.sp1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/registration")
    public String registration(@ModelAttribute("message") String message) {
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String regUser(Model model, User user) {
        if (!userService.isExist(user.getUsername())) {
            userService.addUser(user);
            return "redirect:/";
        } else {
            model.addAttribute("message", "Failed registration!");
        }
        return "registration";
    }
}
