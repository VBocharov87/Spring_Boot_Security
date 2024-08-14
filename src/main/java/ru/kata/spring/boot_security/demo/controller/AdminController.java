package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService service;

    @Autowired
    public AdminController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public String printWelcome(Model model) {
        List<User> users = service.getAllUsers();
        model.addAttribute("users", users);
        return "admin_page";
    }

    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", service.getAllRoles());
        return "add";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user,
                           @RequestParam(required = false) List<Long> roles, Model model) {
        if (roles == null || roles.isEmpty()) {
            model.addAttribute("user", user);
            model.addAttribute("roles", service.getAllRoles());
            model.addAttribute("error", "Please select at least one role.");
            System.out.println("error");
            return "add";
        }

        user.setRoles(service.findAllById(roles));
        service.saveUpdateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/update")
    public String updateUser(@RequestParam("id") Long id, Model model) {
        User user = service.getUser(id);
        System.out.println(user);
        model.addAttribute("user", user);
        model.addAttribute("roles", service.getAllRoles());
        return "add";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        service.deleteUser(id);
        return "redirect:/admin";
    }
}
