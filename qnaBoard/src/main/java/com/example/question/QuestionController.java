package com.example.question;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.domain.CustomerList;
import com.example.domain.HttpSessionUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * QuestionController
 */
@Controller
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/form")
    public String form(HttpSession session) {
        if(!HttpSessionUtils.isLoginUser(session)){
            return "redirect:/users/login";
        }
        return "/qna/form";
    }

    @PostMapping("")
    public String create(String title, String contents, HttpSession session)
    {
        if(!HttpSessionUtils.isLoginUser(session)){
            return "redirect:/users/login";
        }
        CustomerList sessionUser = HttpSessionUtils.getUserFromSession(session);
        Question newQuestion = new Question(sessionUser, title, contents);
        questionRepository.save(newQuestion);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model){
        model.addAttribute("question", questionRepository.findById(id).get());
        return "/qna/show";
    }
    
    @GetMapping("/{id}/form")
    public String updateForm(@PathVariable Long id, Model model, HttpSession session, 
    HttpServletResponse response) throws IOException{
        if (HttpSessionUtils.isLoginUser(session)==false) {
            System.out.println("업데이트 폼으로 넘어가지 못함");
            return "/user/login";
        }
        CustomerList sessionedUser = HttpSessionUtils.getUserFromSession(session);
        System.out.println("sessionedUser = " + sessionedUser.getId() + "\n" + "id = " + id);
        if (!id.equals(sessionedUser.getId())) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('본인 게시글만 수정할 수 있습니다.'); location.href='/users/login';</script>");
            out.flush();
        }

        model.addAttribute("question", questionRepository.findById(id).get());
        return "/qna/updateForm";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, String title, String contents){
        Question question = questionRepository.findById(id).get();
        question.update(title, contents);
        questionRepository.save(question);
        return String.format("redirect:/questions/%d", id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        questionRepository.deleteById(id);
        return "redirect:/";
    }
    
}