package com.capstoneproject.controller;
import com.capstoneproject.dto.QuizDTO;
import com.capstoneproject.service.QuizService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class QuizControllerTest {
    @Mock
    private QuizService quizService;

    @InjectMocks
    private QuizController quizController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllQuiz() {
        List<QuizDTO> quizzes = new ArrayList<>();
        quizzes.add(new QuizDTO(1L, "Quiz1", "Description1", 5, 1L));
        quizzes.add(new QuizDTO(2L, "Quiz2", "Description2", 10, 2L));
        when(quizService.getAllQuiz()).thenReturn(quizzes);
        ResponseEntity<Object> response = quizController.getAllQuiz();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        @SuppressWarnings("unchecked")
        List<QuizDTO> responseQuizzes = (List<QuizDTO>) response.getBody();
        assertNotNull(responseQuizzes);
        assertEquals(2, responseQuizzes.size());
        assertEquals("Quiz1", responseQuizzes.get(0).getQuizName());
    }

    @Test
    public void testGetQuizById() {
        Long quizId = 1L;
        QuizDTO quizDTO = new QuizDTO(quizId, "Quiz1", "Description1", 5, 1L);
        when(quizService.getQuizById(quizId)).thenReturn(quizDTO);
        ResponseEntity<Object> response = quizController.getQuizById(quizId);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        QuizDTO responseQuiz = (QuizDTO) response.getBody();
        assertNotNull(responseQuiz);
        assertEquals(quizId, responseQuiz.getQuizId());
        assertEquals("Quiz1", responseQuiz.getQuizName());
    }

    @Test
    public void testAddQuiz() {
        QuizDTO quizDTO = new QuizDTO(null, "New Quiz", "New Description", 5, 1L);
        when(quizService.addQuiz(quizDTO)).thenReturn(quizDTO);
        ResponseEntity<Object> response = quizController.addQuiz(quizDTO);
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        QuizDTO addedQuiz = (QuizDTO) response.getBody();
        assertNotNull(addedQuiz);
        assertEquals("New Quiz", addedQuiz.getQuizName());
    }

    @Test
    public void testDeleteQuiz() {
        Long quizId = 1L;
        ResponseEntity<Object> response = quizController.deleteQuiz(quizId);
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testUpdateQuiz() {
        Long quizId = 1L;
        QuizDTO updatedQuizDTO = new QuizDTO(null, "Updated Quiz", "Updated Description", 10, 1L);
        when(quizService.updateQuiz(quizId, updatedQuizDTO)).thenReturn(updatedQuizDTO);
        ResponseEntity<Object> response = quizController.updateQuiz(quizId, updatedQuizDTO);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        QuizDTO updatedQuiz = (QuizDTO) response.getBody();
        assertNotNull(updatedQuiz);
        assertEquals("Updated Quiz", updatedQuiz.getQuizName());
    }


    @Test
    public void testGetQuizByCategoryId() throws Exception{
        Long categoryId = 1L;
        List<QuizDTO> quizList = new ArrayList<>();
        quizList.add(new QuizDTO(1L, "Quiz 1", "Quiz 1 Description", 7, 9L));
        quizList.add(new QuizDTO(2L, "Quiz 2", "Quiz 2 Description", 8, 10L));
        when(quizService.getQuizByCategoryId(categoryId)).thenReturn(quizList);
        ResponseEntity<Object> response = quizController.getQuizByCategoryId(categoryId);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        @SuppressWarnings("unchecked")
        List<QuizDTO> responseQuizList = (List<QuizDTO>) response.getBody();
        assertNotNull(responseQuizList);
        assertEquals(2, responseQuizList.size());
        
        QuizDTO quiz1 = responseQuizList.get(0);
        assertEquals(1L, quiz1.getQuizId());
        assertEquals("Quiz 1", quiz1.getQuizName());
        assertEquals("Quiz 1 Description", quiz1.getQuizDescription());
        assertEquals(7, quiz1.getNumOfQuestions());
        assertEquals(9L, quiz1.getCategoryId());
        
        QuizDTO quiz2 = responseQuizList.get(1);
        assertEquals(2L, quiz2.getQuizId());
        assertEquals("Quiz 2", quiz2.getQuizName());
        assertEquals("Quiz 2 Description", quiz2.getQuizDescription());
        assertEquals(8, quiz2.getNumOfQuestions());
        assertEquals(10L, quiz2.getCategoryId());
    }
}
