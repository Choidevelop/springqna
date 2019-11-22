package com.example.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ApiUserController
 */
@RestController
@RequestMapping("/api/users")
public class ApiUserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public CustomerList show(@PathVariable Long id){
        return userRepository.findById(id).get();
    }
}