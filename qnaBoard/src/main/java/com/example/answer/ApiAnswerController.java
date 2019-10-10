package com.example.answer;

import javax.servlet.http.HttpSession;

import com.example.domain.CustomerList;
import com.example.domain.HttpSessionUtils;
import com.example.question.Question;
import com.example.question.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AnswerController
 */
@RestController
@RequestMapping("/api/questions/{questionId}/answers")
public class ApiAnswerController {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @PostMapping("")
    public Answer create(@PathVariable Long questionId, String contents, HttpSession session){
        if (!HttpSessionUtils.isLoginUser(session)) {
            return null;
        }

        CustomerList user = HttpSessionUtils.getUserFromSession(session);
        Question question = questionRepository.findById(questionId).get();
        Answer answer = new Answer(user, question, contents);
        System.out.println("ajax 통신");
        return answerRepository.save(answer);
    }

    
}