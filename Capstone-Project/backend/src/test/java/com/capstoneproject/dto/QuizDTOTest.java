package com.capstoneproject.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuizDTOTest {

    private QuizDTO quizDto;

    @BeforeEach
    public void setUp() {
        quizDto = new QuizDTO(1L, "Test Quiz", "This is Quiz Description",10, 4, 2L);
    }

    @Test
    public void testConstructorWithNullCategoryId() {
        Long quizId = 1L;
        String quizName = "Test Quiz";
        String quizDescription = "This is the Description of the Test Quiz";
        int numOfQuestions = 10;
        int time = 4;
        Long categoryId = null;
        
        QuizDTO quizDtoTest = new QuizDTO(quizId, quizName, quizDescription, numOfQuestions, time, categoryId);
        assertEquals(quizId, quizDtoTest.getQuizId());
        assertEquals(quizName, quizDtoTest.getQuizName());
        assertEquals(quizDescription, quizDtoTest.getQuizDescription());
        assertEquals(-1L, quizDtoTest.getCategoryId());
    }
    
    @Test
    public void testQuizDTOConstructor() {
        QuizDTO newQuizDto = new QuizDTO();
        assertNull(newQuizDto.getQuizId());
        assertNull(newQuizDto.getQuizName());
        assertNull(newQuizDto.getQuizDescription());
        assertEquals(0,newQuizDto.getNumOfQuestions());
        assertNull(null);
    }

    @Test
    public void testParametrizedConstructor() {
        assertEquals(1L, quizDto.getQuizId());
        assertEquals("Test Quiz", quizDto.getQuizName());
        assertEquals("This is Quiz Description", quizDto.getQuizDescription());
        assertEquals(10, quizDto.getNumOfQuestions());
        assertEquals(2l, quizDto.getCategoryId());
    }
    
    @Test
    public void testGettersAndSetters() {
        quizDto.setQuizId(3L);
        quizDto.setQuizName("Updated Quiz");
        quizDto.setQuizDescription("Updated Description");
        quizDto.setNumOfQuestions(5);
        quizDto.setCategoryId(4L);
        
        assertEquals(3L, quizDto.getQuizId());
        assertEquals("Updated Quiz", quizDto.getQuizName());
        assertEquals("Updated Description", quizDto.getQuizDescription());
        assertEquals(5, quizDto.getNumOfQuestions());
        assertEquals(4L, quizDto.getCategoryId());
    }
    
    @Test
    public void testGetCategoryId() {
        Long categoryIdNotNull = 1L;
        QuizDTO quizDtoNotNull = new QuizDTO(1L, "Test Quiz", "Test Description",7, 3, categoryIdNotNull);
        assertEquals(categoryIdNotNull, quizDtoNotNull.getCategoryId());
        
        QuizDTO quizDtoNull = new QuizDTO(2L, "Test Quiz 2", "Test 2 Description", 10, 4, null);
        assertEquals(-1L, quizDtoNull.getCategoryId());
    }
}
