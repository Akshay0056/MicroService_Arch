package com.quiz.repository;


import com.quiz.entites.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRespository extends JpaRepository<Quiz,Long> {
}
