package com.example.home;

import java.util.ArrayList;

import com.example.question.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * HelloController
 */
@Controller
public class HelloController {

    @Autowired
    private QuestionRepository questionRepository;
    @GetMapping("")
    public String index(Model model){
        model.addAttribute("questions", questionRepository.findAll());
        return "index";
    }
}