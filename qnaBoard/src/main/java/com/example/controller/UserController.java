package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.model.CustomerList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * UserController
 */
@Controller
public class UserController {
    private List<CustomerList> users = new ArrayList<CustomerList>();
    @PostMapping("/create")
    public String createUser(CustomerList list){
        
        users.add(list);
        System.out.println("user : " + list);
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String listUser(Model model){

        System.out.println("/list : " + users);
        model.addAttribute("customlist", users);
        return "list";
    }
}