package com.capstoneproject.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.capstoneproject.models.Category;
import com.capstoneproject.models.Quiz;
import com.capstoneproject.service.CategoryService;
import com.capstoneproject.service.QuizService;

@SuppressWarnings("deprecation")
class QuizControllerTest {

    @Mock
    private QuizService quizService;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private QuizController quizController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllQuiz() {
        List<Quiz> quiz = new ArrayList<>();
        quiz.add(new Quiz());
        quiz.add(new Quiz());
        
        when(quizService.getAllQuiz()).thenReturn(quiz);
        ResponseEntity<List<Quiz>> response = quizController.getAllQuiz();
        
        verify(quizService, times(1)).getAllQuiz();
        assert(response.getStatusCode()==HttpStatus.OK);
    }

    @Test
    void testGetQuizById() {
        Long quizId = 12L;
        Quiz quiz = new Quiz();
        when(quizService.getQuizById(quizId)).thenReturn(quiz);
        ResponseEntity<Quiz> response = quizController.getQuizById(quizId);
        verify(quizService, times(1)).getQuizById(quizId);
        assert(response.getStatusCode()==HttpStatus.OK);
        assert(response.getBody()!=null);
    }
    
    @Test
    void testAddQuiz() {
        Quiz quiz = new Quiz();
        when(quizService.addQuiz(quiz)).thenReturn(quiz);
        ResponseEntity<Quiz> response = quizController.addQuiz(quiz);
        verify(quizService, times(1)).addQuiz(quiz);
        assert(response.getStatusCode()==HttpStatus.OK);
        assert(response.getBody()!= null);
    }

    @Test
    void testDeleteQuiz() {
        Long quizId = 12L;
        doNothing().when(quizService).deleteQuiz(quizId);
        ResponseEntity<Void> response = quizController.deleteQuiz(quizId);
        verify(quizService,times(1)).deleteQuiz(quizId);
        assert(response.getStatusCode()==HttpStatus.NO_CONTENT);
    }

    @Test
    void testGetQuizByCategoryId() {
        Long categoryId = 12L;
        Category category = new Category();
        when(categoryService.getCategoryById(categoryId)).thenReturn(category);
        List<Quiz> quizList = new ArrayList<>();
        when(quizService.getQuizByCategoryId(category)).thenReturn(quizList);
        ResponseEntity<List<Quiz>> response = quizController.getQuizByCategoryId(categoryId);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(quizList, response.getBody());
    }
    
    
    @Test
    void testUpdateQuiz() {
        Long quizId = 12L;
        Quiz quiz = new Quiz();
        when(quizService.updateQuiz(quizId, quiz)).thenReturn(quiz);
        ResponseEntity<Quiz> response = quizController.updateQuiz(quizId, quiz);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(quiz, response.getBody());
    }
    
    @Test
    void testGetQuizByCategoryIdNotFound() {
        Long categoryId = 1L;
        when(categoryService.getCategoryById(categoryId)).thenReturn(null);
        ResponseEntity<List<Quiz>> response = quizController.getQuizByCategoryId(categoryId);
        assertEquals(404, response.getStatusCodeValue());
    }
}
