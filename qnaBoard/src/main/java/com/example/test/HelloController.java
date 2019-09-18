package com.example.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * HelloController
 */
@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "welcome";
    }
    @GetMapping("/index")
    public String index(){
        return "index";
    }
    @GetMapping("/form")
    public String form(){
        return "form";
    }
}