package com.example.answer;

import javax.servlet.http.HttpSession;

import com.example.domain.CustomerList;
import com.example.domain.HttpSessionUtils;
import com.example.question.PermissionResult;
import com.example.question.Question;
import com.example.question.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
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
        question.addAnswer();
        System.out.println("ajax 통신");
        return answerRepository.save(answer);
    }

    @DeleteMapping("/{id}")
    public PermissionResult delete(@PathVariable Long questionId, @PathVariable Long id, 
    HttpSession session, Model model){
        if(!HttpSessionUtils.isLoginUser(session)){
            return PermissionResult.fail("로그인이 필요합니다.");
        }

        Answer answer = answerRepository.findById(id).get();
        CustomerList loginUser = HttpSessionUtils.getUserFromSession(session);

        if(!answer.isSameWriter(loginUser)){
            return PermissionResult.fail("자신의 글만 삭제 할 수 있습니다.");
        }

        answerRepository.deleteById(id);
        Question question = questionRepository.findById(questionId).get();
        question.deleteAnswer();
        questionRepository.save(question);
        System.out.println("퍼미션 결과 : " + PermissionResult.deleteCountOk(question));
        return PermissionResult.deleteCountOk(question);

    }

    
}