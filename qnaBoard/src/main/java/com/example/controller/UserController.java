package com.example.controller;

import com.example.domain.CustomerList;
import com.example.domain.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * UserController
 */
@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("")
    public String createUser(CustomerList list) {

        userRepository.save(list);
        System.out.println("user : " + list);
        return "redirect:/users";
    }

    @GetMapping("")
    public String listUser(Model model) {

        model.addAttribute("customlist", userRepository.findAll());
        return "./user/list";
    }

    @GetMapping("/login")
    public String loginUser(Model model) {
        return "./user/login";
    }

    @GetMapping("/form")
    public String form() {
        return "/user/form";
    }

    @GetMapping("/{id}/form")
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", userRepository.findById(id).get());
        return "/user/updateForm";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, CustomerList newUser){
        CustomerList user = userRepository.findById(id).get();
        user.update(newUser);
        userRepository.save(user);
        return "redirect:/users";
    }
}