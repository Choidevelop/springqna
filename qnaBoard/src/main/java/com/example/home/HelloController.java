package com.example.home;

import com.example.controller.PageRequest;
import com.example.question.Question;
import com.example.question.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
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
        // PageRequest pageRequest = new PageRequest();
        // pageRequest.setDirection(Direction.DESC);
        // pageRequest.setSize(10);
        // Question question = new Question();
        // System.out.println("pageRequest : " + pageRequest.of());
        model.addAttribute("questions", questionRepository.findAll());
        return "index";
    }

    @GetMapping("/test")
    public String testIndex(Model model, PageRequest pageable){
        pageable.setDirection(Direction.DESC);
        pageable.setSize(10);
        System.out.println("pageRequest : " + questionRepository.findAll(pageable.of()));
        model.addAttribute("questions", questionRepository.findAll(pageable.of()));
        return "dbTest";
    }

    @GetMapping("/test1")
    public String leafTest(Model model, PageRequest pageable, Pageable page){
        if(setPage(pageable, page, model)){
            return "leafTest";    
        }
        return "";
    }

    private boolean setPage(PageRequest pageable, Pageable page, Model model){
        int reqPage = (page.getPageNumber()==0) ? 0 : page.getPageNumber()-1;
        System.out.println("페이지값은 >>>: " + page.getPageNumber());
        Page<Question> questionPage = null;
        if(reqPage<0){
            return false;
        }
        if(reqPage==0){
            System.out.println("페이지가 0이면 여기와라~");
            pageable.setDirection(Direction.DESC);
            pageable.setSize(10);
            questionPage = questionRepository.findAll(pageable.of());
            model.addAttribute("questions", questionPage);
        }
        if(reqPage>0){
            pageable.setDirection(Direction.DESC);
            pageable.setSize(10);
            pageable.setPage(reqPage);
            questionPage = questionRepository.findAll(pageable.of());
            model.addAttribute("questions", questionPage);
        }
        if(questionPage.getTotalPages()<=reqPage){
            pageable.setDirection(Direction.DESC);
            pageable.setSize(10);
            pageable.setPage(questionPage.getTotalPages()-1);
            questionPage = questionRepository.findAll(pageable.of());
            model.addAttribute("questions", questionPage);
            System.out.println("총 페이지를 넘어가는 숫자가 입력되어 총페이지수로 저장");
        }
        return true;
    }
}