package com.capstoneproject.controller;
import com.capstoneproject.dto.QuizDTO;
import com.capstoneproject.exceptions.AlreadyExistsException;
import com.capstoneproject.exceptions.ElementNotExistsException;
import com.capstoneproject.exceptions.EntityNotFoundException;
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

import static org.junit.Assert.assertThrows;
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
        assertEquals(HttpStatus.OK, response.getStatusCode());
        QuizDTO addedQuiz = (QuizDTO) response.getBody();
        assertNotNull(addedQuiz);
        assertEquals("New Quiz", addedQuiz.getQuizName());
    }

    @Test
    public void testAddQuizAlreadyExists() {
        QuizDTO quizDTO = new QuizDTO(null, "Existing Quiz", "Description", 10, 1L);
        when(quizService.addQuiz(quizDTO)).thenThrow(new AlreadyExistsException());
        assertThrows(AlreadyExistsException.class, () -> quizController.addQuiz(quizDTO));
    }

    @Test
    public void testDeleteQuiz() {
        Long quizId = 1L;
        ResponseEntity<Object> response = quizController.deleteQuiz(quizId);
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testDeleteQuizNotFound() {
        Long quizId = 1L;
        doThrow(new ElementNotExistsException()).when(quizService).deleteQuiz(quizId);
        assertThrows(ElementNotExistsException.class, () -> quizController.deleteQuiz(quizId));
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
    public void testUpdateQuizNotFound() {
        Long quizId = 1L;
        QuizDTO updatedQuizDTO = new QuizDTO(null, "Updated Quiz", "Updated Description", 10, 1L);
        when(quizService.updateQuiz(quizId, updatedQuizDTO)).thenThrow(new EntityNotFoundException("Entity not found"));
        assertThrows(EntityNotFoundException.class, () -> quizController.updateQuiz(quizId, updatedQuizDTO));
    }
}
