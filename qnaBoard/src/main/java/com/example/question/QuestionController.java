package com.example.question;

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
        if (!HttpSessionUtils.isLoginUser(session)) {
            return "redirect:/users/login";
        }
        return "/qna/form";
    }

    @PostMapping("")
    public String create(String title, String contents, HttpSession session) {
        if (!HttpSessionUtils.isLoginUser(session)) {
            return "redirect:/users/login";
        }
        CustomerList sessionUser = HttpSessionUtils.getUserFromSession(session);
        Question newQuestion = new Question(sessionUser, title, contents);
        questionRepository.save(newQuestion);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model) {
        model.addAttribute("question", questionRepository.findById(id).get());
        return "/qna/show";
    }

    @GetMapping("/{id}/form")
    public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
        Question question = questionRepository.findById(id).get();
        PermissionResult result = valid(session, question);
        if (!result.isVaild()) {
            model.addAttribute("errorMessage", result.getErrorMessage());

            System.out.println("errorMessage = " + result.getErrorMessage());
            return "/user/login";
        }
        model.addAttribute("question", question);
        System.out.println("question = " + question);
        return "/qna/updateForm";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, String title, String contents, Model model, HttpSession session) {
        Question question = questionRepository.findById(id).get();
        PermissionResult result = valid(session, question);
        if (!result.isVaild()) {
            model.addAttribute("errorMessage", result.getErrorMessage());

            System.out.println("errorMessage = " + result.getErrorMessage());
            return "/user/login";
        }
        question.update(title, contents);
        questionRepository.save(question);
        model.addAttribute("question", question);
        System.out.println("question = " + question);
        return String.format("redirect:/questions/%d", id);
        // session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, loginUser);

        // System.out.println("session에 저장된 아이디는 = " + loginUser);

    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id, Model model, HttpSession session) {
        Question question = questionRepository.findById(id).get();
        PermissionResult result = valid(session, question);
        if (!result.isVaild()) {
            model.addAttribute("errorMessage", result.getErrorMessage());

            System.out.println("errorMessage = " + result.getErrorMessage());
            return "/user/login";
        }
        questionRepository.deleteById(id);
        return "redirect:/";
    }

    /*
     * 로그인 정보 및 사용자 권환 확인 메소드
     */
    private PermissionResult valid(HttpSession session, Question question) {
        if (!HttpSessionUtils.isLoginUser(session)) {
            return PermissionResult.fail("로그인 후 이용할 수 있습니다.");
        }
        CustomerList loginUser = HttpSessionUtils.getUserFromSession(session);
        if (!question.isSameWriter(loginUser)) {
            return PermissionResult.fail("자신이 쓴 글만 수정, 삭제 가능합니다.");
        }
        return PermissionResult.ok();
    }

}