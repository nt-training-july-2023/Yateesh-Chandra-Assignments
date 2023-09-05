package com.capstoneproject.models;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(category.getCategoryId(), quiz.getCategory().getCategoryId());
    }
    
    @Test
    public void testQuestionListGetterAndSetter() {
        Quiz quiz = new Quiz();
        Question question1 = new Question();
        Question question2 = new Question();
        
        quiz.getQuestions().add(question1);
        quiz.getQuestions().add(question2);
        
        assertEquals(0, quiz.getQuestions().size());
        assertFalse(quiz.getQuestions().contains(question1));
        assertFalse(quiz.getQuestions().contains(question2));
    }
    
    @Test
    public void testAllArgsConstructor() {
        Long quizId = 1L;
        String quizName = "TestQuiz";
        String quizDescription = "Quiz Description";
        int numOfQuestions = 10;

        Quiz quiz = new Quiz(quizId, quizName, quizDescription, numOfQuestions);
        
        assertEquals(quizId, quiz.getQuizId());
        assertEquals(quizName, quiz.getQuizName());
        assertEquals(quizDescription, quiz.getQuizDescription());
        assertEquals(numOfQuestions, quiz.getNumOfQuestions());
    }
}
