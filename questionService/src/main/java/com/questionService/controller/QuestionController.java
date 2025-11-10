package com.questionService.controller;


import com.questionService.Service.QustionService;
import com.questionService.entites.Questions;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private QustionService qustionService;

    public QuestionController(QustionService qustionService) {
        this.qustionService = qustionService;
    }


    //create

    @PostMapping
    public Questions create(@RequestBody Questions questions){
        return qustionService.create(questions);

    }

    @GetMapping
    public List<Questions> getAll() {
        return qustionService.get();
    }

    //get all
    @GetMapping("/{questionId}")
    public  Questions getAll(@PathVariable Long questionId){
        return qustionService.getone(questionId);
    }

    @GetMapping("/quiz/{quizId}")
//get all questions of specific quiz
    public List<Questions> getQuestionsOfQuiz(@PathVariable Long quizId){
     return qustionService.getQuestionOfQuiz(quizId);
    }

}



