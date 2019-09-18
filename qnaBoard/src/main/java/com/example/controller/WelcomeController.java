package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * WelcomeController
 */
@Controller
public class WelcomeController {

    @GetMapping("/hihi")
    public String getMethod(){
        return "nabi";
    }
}