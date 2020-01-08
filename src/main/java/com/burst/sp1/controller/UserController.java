package com.burst.sp1.controller;

import com.burst.sp1.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping(value = "/user")
    public String simpleUser(Model model) {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("message", "Hello " + user.getUsername() + "!");
        model.addAttribute("user", user);
        return "user";
    }

}
