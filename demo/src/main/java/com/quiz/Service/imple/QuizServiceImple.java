package com.quiz.Service.imple;

import com.quiz.Service.QuizService;
import com.quiz.entites.Quiz;
import com.quiz.repository.QuizRespository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizServiceImple implements QuizService {

    private final QuizRespository quizRespository;
    private final QuestionClient questionClient;

    public QuizServiceImple(QuestionClient questionClient, QuizRespository quizRespository) {
        this.questionClient = questionClient;
        this.quizRespository = quizRespository;
    }

    @Override
    public Quiz add(Quiz quiz) {
        // Make sure Hibernate treats this as a NEW entity
        quiz.setId(null);
        try {
            return quizRespository.save(quiz);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save quiz: " + e.getMessage());
        }
    }


    @Override
    public List<Quiz> get() {
        List<Quiz> quizzes = quizRespository.findAll();

        return quizzes.stream().map(quiz -> {
            try {
                System.out.println("📡 Fetching questions for quiz ID: " + quiz.getId());
                quiz.setQuestions(questionClient.getQuestionsOfQuiz(quiz.getId()));
                System.out.println("✅ Questions fetched successfully for quiz ID: " + quiz.getId());
            } catch (Exception e) {
                System.out.println("❌ Failed to fetch questions for quiz ID: " + quiz.getId());
                e.printStackTrace();
                quiz.setQuestions(Collections.emptyList()); // fallback if Feign fails
            }
            return quiz;
        }).collect(Collectors.toList());
    }

    @Override
    public Quiz get(Long id) {
        Quiz quiz = quizRespository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz not found with ID: " + id));

        try {
            System.out.println("📡 Calling Question Service for quiz ID: " + id);
            quiz.setQuestions(questionClient.getQuestionsOfQuiz(quiz.getId()));
            System.out.println("✅ Questions fetched successfully for quiz ID: " + id);
        } catch (Exception e) {
            System.out.println("❌ ERROR while fetching questions for quiz ID: " + id);
            e.printStackTrace();
            quiz.setQuestions(Collections.emptyList());
            throw new RuntimeException("Question Service failed for quiz ID " + id + ": " + e.getMessage(), e);
        }

        return quiz;
    }
}
