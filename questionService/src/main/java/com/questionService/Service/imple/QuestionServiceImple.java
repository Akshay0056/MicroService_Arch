package com.questionService.Service.imple;

import com.questionService.QuestionRespository.QuestionRespository;
import com.questionService.Service.QustionService;
import com.questionService.entites.Questions;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Service

public class QuestionServiceImple implements QustionService {

private QuestionRespository questionRespository;

    public QuestionServiceImple(QuestionRespository questionRespository) {
        this.questionRespository = questionRespository;
    }

    @Override

    public Questions create(Questions questions) {
        return questionRespository.save(questions);
    }

    @Override
    public List<Questions> get() {
        return  questionRespository.findAll();
    }

    @Override
    public Questions getone(Long id) {
        return questionRespository.findById(id).orElseThrow(()-> new RuntimeException("questions not found"));
    }

    @Override
    public List<Questions> getQuestionOfQuiz(Long quizId) {
        return  questionRespository.findByQuizId(quizId);
    }
}
