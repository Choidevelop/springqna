package com.example.model;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * WelcomeCustomer
 */
@Controller
public class WelcomeCustomer {

    @GetMapping("/nabi")
    public String welcome(Model model){
        model.addAttribute("name", "nabi");
        return "nabi";

    }
    // @ModelAttribute("wrapped")
    // public Mustache.Lambda wrapped(Model model){
    //     return (frag, out) -> {
    //         String bodystring = frag.execute();
    //         System.out.println("bodystring :" + bodystring);
    //         out.append(bodystring);
    //         System.out.println("out.append" + out.append(bodystring));
    //     };
    // }
    
    

}