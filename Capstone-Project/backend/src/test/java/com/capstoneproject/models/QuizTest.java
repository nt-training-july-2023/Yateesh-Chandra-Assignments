package com.capstoneproject.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class QuizTest {

    @Test
    public void testQuizGetterAndSetter() {
        Quiz quiz = new Quiz();
        quiz.setQuizId(12L);
        quiz.setQuizName("DataType in Java");
        quiz.setQuizDescription("This contains the Quiz of DataTypes in java");
        quiz.setNumOfQuestions(10);
        
        assertEquals(12L, quiz.getQuizId());
        assertEquals("DataType in Java", quiz.getQuizName());
        assertEquals("This contains the Quiz of DataTypes in java", quiz.getQuizDescription());
        assertEquals(10, quiz.getNumOfQuestions());
    }

    @Test
    public void testQuestionGetterAndSetter() {
        Quiz quiz = new Quiz();
        Category category = new Category();
        quiz.setCategory(category);
        assertEquals(category, quiz.getCategory());
    }
    
    @Test
    public void testQuestionListGetterAndSetter() {
        Quiz quiz = new Quiz();
        Question question1 = new Question();
        Question question2 = new Question();
        
        quiz.getQuestions().add(question1);
        quiz.getQuestions().add(question2);
        
        assertEquals(2, quiz.getQuestions().size());
        assertTrue(quiz.getQuestions().contains(question1));
        assertTrue(quiz.getQuestions().contains(question2));
    }
    
    @Test
    public void testAllArgsConstructor() {
        Long quizId = 1L;
        String quizName = "TestQuiz";
        String quizDescription = "Quiz Description";
        int numOfQuestions = 10;
        Category category = new Category();
        Question question1 = new Question();
        Question question2 = new Question();
        List<Question> questions = new ArrayList<>();

        Quiz quiz = new Quiz(quizId, quizName, quizDescription, numOfQuestions, category, questions);
        quiz.getQuestions().add(question1);
        quiz.getQuestions().add(question2);

        assertEquals(quizId, quiz.getQuizId());
        assertEquals(quizName, quiz.getQuizName());
        assertEquals(quizDescription, quiz.getQuizDescription());
        assertEquals(numOfQuestions, quiz.getNumOfQuestions());
        assertEquals(category, quiz.getCategory());
        assertTrue(quiz.getQuestions().contains(question1));
        assertTrue(quiz.getQuestions().contains(question2));
    }
}
