package com.example.controller;

import com.example.domain.CustomerList;
import com.example.domain.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * UserController
 */
@Controller
public class UserController {
    @Autowired
    private UserRepository UserRepository;
    @PostMapping("/create")
    public String createUser(CustomerList list){
        
        UserRepository.save(list);
        System.out.println("user : " + list);
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String listUser(Model model){

        model.addAttribute("customlist", UserRepository.findAll());
        return "./user/list";
    }
}