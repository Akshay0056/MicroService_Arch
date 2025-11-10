package com.questionService.QuestionRespository;

import com.questionService.entites.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRespository extends JpaRepository<Questions,Long> {


    List<Questions> findByQuizId(Long quizId);
}
