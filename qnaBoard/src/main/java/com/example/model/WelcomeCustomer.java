package com.example.model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * WelcomeCustomer
 */
@Controller
public class WelcomeCustomer {

    @GetMapping("/nabi")
    public String welcome(){
        return "nabi";
    }    
}