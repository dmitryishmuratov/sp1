package com.burst.sp1.controller;

import com.burst.sp1.model.User;
import com.burst.sp1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    private final UserService service;
    private final Environment environment;

    @Autowired
    public AdminController(UserService service, BCryptPasswordEncoder passwordEncoder, Environment environment) {
        this.service = service;
        this.environment = environment;
    }

    @GetMapping(value = "/admin")
    public String listUsers(Model model,
                            @ModelAttribute("user") User user,
                            @ModelAttribute("message") String message) {
        List<User> users = service.getAllUsers();
        model.addAttribute("users", users);
        return "admin";
    }

    @PostMapping(value = "/admin/add")
    public String addUser(@ModelAttribute("user") User user, Model model, @RequestParam("role") String role) {
        if (!service.isExist(user.getUsername()) && service.addUserRoot(user, role)) {
            model.addAttribute("message", "Пользователь " + user.getUsername() + " добавлен!");
            return "redirect:/admin";
        } else {
            model.addAttribute("message", environment.getRequiredProperty("noAdd"));
        }
        return "admin/add";
    }

    @GetMapping("/admin/edit")
    public String edit() {
        return "admin/edit";
    }

    @PostMapping("/admin/edit")
    public String edit(@RequestParam("id") int id, @ModelAttribute("user") User user,
                       @RequestParam("role") String role) {
        User newuser = service.getUserById(id);
        newuser.setUsername(user.getUsername());
        newuser.setEmail(user.getEmail());
        newuser.setPassword(user.getPassword());
        service.editUser(newuser, role);
        return "redirect:/admin";
    }

    @GetMapping("admin/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        service.deleteById(id);
        return "redirect:/admin";
    }
}
