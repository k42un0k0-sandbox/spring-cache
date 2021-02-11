package com.example.spring_cache.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UsersController {

    @GetMapping("")
    public String index() {
        return "users/index";
    }

    @GetMapping("{id}")
    public String show(@PathVariable String id, Model model) {
        model.addAttribute("id", id);
        return "users/show";
    }
}
