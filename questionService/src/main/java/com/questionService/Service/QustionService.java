package com.questionService.Service;


import com.questionService.entites.Questions;

import java.util.List;

public interface QustionService {

    Questions create(Questions questions);
    List<Questions> get();
    Questions getone(Long id);
    List<Questions> getQuestionOfQuiz(Long quizId);


}
