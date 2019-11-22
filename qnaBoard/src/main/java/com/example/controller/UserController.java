package com.example.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.domain.CustomerList;
import com.example.domain.HttpSessionUtils;
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
    public String loginForm(Model model) {
        return "/user/login";
    }

    @PostMapping("/login")
    public String loginUser(String userId, String userPw, HttpSession session) {
        System.out.println("userId = " + userId + "\n" + "userPw = " + userPw);
        CustomerList user = userRepository.findByUserId(userId);
        if (user == null) {
            return "redirect:/users/login";
        }
        if (!userPw.equals(user.getUserPw())) {
            return "redirect:/users/login";
        }

        session.setAttribute("login", user);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("login");
        return "redirect:/";
    }

    @GetMapping("/form")
    public String form() {
        return "/user/form";
    }

    @GetMapping("/{id}/form")
    public String updateForm(@PathVariable Long id, Model model, HttpSession session, HttpServletResponse response)
            throws IOException {
        if (HttpSessionUtils.isLoginUser(session)==false) {
            System.out.println("업데이트 폼으로 넘어가지 못함");
            return "/user/login";
        }
        CustomerList sessionedUser = HttpSessionUtils.getUserFromSession(session);
        if (!id.equals(sessionedUser.getId())) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('잘못된 접근입니다. 로그인하세요.'); location.href='/users/login';</script>");
            out.flush();
        }

        model.addAttribute("user", userRepository.findById(id).get());
        return "/user/updateForm";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, CustomerList updateUser, HttpSession session) {
        if (HttpSessionUtils.isLoginUser(session)) {
            return "/user/login";
        }
        CustomerList sessionedUser = HttpSessionUtils.getUserFromSession(session);
        if (!id.equals(sessionedUser.getId())) {
            return "/user/login";
        }
        CustomerList user = userRepository.findById(id).get();
        user.update(updateUser);
        userRepository.save(user);
        return "redirect:/users";
    }
}