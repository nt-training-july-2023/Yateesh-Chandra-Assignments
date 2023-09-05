package com.capstoneproject.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capstoneproject.models.Category;
import com.capstoneproject.models.Quiz;
import com.capstoneproject.repository.QuizRepository;

import jakarta.persistence.EntityNotFoundException;

class QuizServiceTest {

    @Mock
    private QuizRepository quizRepository;
    
    @InjectMocks
    private QuizService quizService;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testGetAllQuiz() {
        List<Quiz> quiz = new ArrayList<>();
        when(quizRepository.findAll()).thenReturn(quiz);
        List<Quiz> actualQuiz = quizService.getAllQuiz();
        assertEquals(quiz, actualQuiz);
    }
    
    @Test
    public void testAddQuiz() {
        Quiz quizNew = new Quiz();
        Quiz savedQuiz = new Quiz();
        when(quizRepository.save(quizNew)).thenReturn(savedQuiz);
        Quiz actualQuiz = quizService.addQuiz(quizNew);
        assertEquals(savedQuiz, actualQuiz);
    }
    
    @Test
    public void testGetCategoryById() {
        Long quizId = 5L;
        Quiz existingQuiz = new Quiz();
        
        when(quizRepository.findById(quizId)).thenReturn(Optional.of(existingQuiz));
        Quiz actualQuiz = quizService.getQuizById(quizId);
        assertEquals(actualQuiz, existingQuiz);
    }
    
    @Test
    public void testGetQuizByIdnotFound() {
        Long quizId = 7L;
        
        when(quizRepository.findById(quizId)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, ()-> quizService.getQuizById(quizId));
    }
    
    @Test
    public void testGetQuizByCategoryId() {
        Category category = new Category();
        List<Quiz> quizzes = new ArrayList<>();
        when(quizRepository.findByCategory(category)).thenReturn(quizzes);
        
        List<Quiz> actualQuizzes = quizService.getQuizByCategoryId(category);
        assertEquals(actualQuizzes, quizzes);
            
        }
    
    @Test
    public void testDeleteQuiz() {
        Long quizId = 6L;
        assertDoesNotThrow(()-> quizService.deleteQuiz(quizId));
        verify(quizRepository, times(1)).deleteById(quizId);
    }
    
    @Test
    public void testUpdateQuiz_Success() {
        Category category = new Category(1L, "CategoryName", "CategoryDescription");
        Quiz quiz = new Quiz(1L, "QuizName", "QuizDescription", 10);
        quiz.setCategory(category);
        Long quizId = 1L;
        Quiz updatedQuiz = new Quiz(quizId, "UpdatedQuizName", "UpdatedQuizDescription", 15);
        updatedQuiz.setCategory(category); // Set the same category

        when(quizRepository.findById(quizId)).thenReturn(Optional.of(quiz));
        when(quizRepository.save(quiz)).thenReturn(updatedQuiz);
        Quiz result = quizService.updateQuiz(quizId, updatedQuiz);
        assertEquals(updatedQuiz.getQuizName(), result.getQuizName());
        assertEquals(updatedQuiz.getQuizDescription(), result.getQuizDescription());
        assertEquals(updatedQuiz.getNumOfQuestions(), result.getNumOfQuestions());
    }
    
    @Test
    public void testUpdateQuizEntityNotFound() {
        Long quizId = 4L;
        Quiz existingQuiz = new Quiz();
        
        when(quizRepository.findById(quizId)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, ()->quizService.updateQuiz(quizId, existingQuiz));
    }
}

