package com.capstoneproject.controller;

import static org.junit.jupiter.api.Assertions.*;
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

import com.capstoneproject.dto.QuestionDTO;
import com.capstoneproject.service.QuestionService;

class QuestionControllerTest {

    @Mock
    private QuestionService questionService;
    
    @InjectMocks
    private QuestionController questionController;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testGetAllQuestion() {
        List<QuestionDTO> questions = new ArrayList<>();
        questions.add(new QuestionDTO(null, "Test Question", "A", "B", "C", "D", "OptionB", 4L));
        questions.add(new QuestionDTO(null, "Test Question 2", "A1", "B2", "C3", "D4", "OptionB", 5L));
        when(questionService.getQuestions()).thenReturn(questions);
        ResponseEntity<List<QuestionDTO>> response = questionController.getQuestions();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<QuestionDTO> responseQuestions = (List<QuestionDTO>) response.getBody();
        assertNotNull(responseQuestions);
        assertEquals(2, responseQuestions.size());
        assertEquals("Test Question", responseQuestions.get(0).getQuestionTitle());
        assertEquals("Test Question 2", responseQuestions.get(1).getQuestionTitle());
    }

    @Test
    public void testGetQuestionById() {
        Long questionId = 9L;
        QuestionDTO questionDTO = new QuestionDTO(questionId, "Question 1", "A", "B", "C", "D", "OptionB", 4L );
        when(questionService.getQuestionById(questionId)).thenReturn(questionDTO);
        ResponseEntity<QuestionDTO> response = questionController.getQuestionById(questionId);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        QuestionDTO responseQuestion = (QuestionDTO) response.getBody();
        assertNotNull(responseQuestion);
        assertEquals(questionId, responseQuestion.getQuestionId());
        assertEquals("Question 1", responseQuestion.getQuestionTitle());
    }

    @Test
    public void testAddQuestion() {
        QuestionDTO questionDto = new QuestionDTO(null, "Question 1", "A", "B", "C", "D", "OptionB", 4L);
        when(questionService.addQuestion(questionDto)).thenReturn(questionDto);
        ResponseEntity<String> response = questionController.addQuestion(questionDto);
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        String responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals("Question added", responseBody);
    }

    @Test
    public void testDeleteQuestion() {
        Long questionId = 3L;
        ResponseEntity<Void> response = questionController.deleteQuestion(questionId);
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testUpdateQuestion() {
        Long questionId = 8L;
        QuestionDTO updatedQuestionDTO = new QuestionDTO(null, "Updated Question", "A", "B", "C", "D", "OptionB", 4L);
        when(questionService.updateQuestion(questionId, updatedQuestionDTO)).thenReturn(updatedQuestionDTO);
        ResponseEntity<String> response = questionController.updateQuestion(questionId, updatedQuestionDTO);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        String responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals("Quiz is updated", responseBody); // Ensure the response body contains the expected message
    }

    @Test
    public void testQuestionByQuizId() {
        Long questionId = 1L;
        List<QuestionDTO> questions = new ArrayList<>();
        questions.add(new QuestionDTO(1L, "Question 1", "A", "B", "C", "D", "OptionB", 4L));
        questions.add(new QuestionDTO(2L, "Question 2", "A", "B", "C", "D", "OptionB", 5L));
        when(questionService.getQuestionByQuizId(questionId)).thenReturn(questions);
        ResponseEntity<List<QuestionDTO>> response = questionController.getQuestionByQuizId(questionId);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<QuestionDTO> responseQuestions = (List<QuestionDTO>) response.getBody();
        assertNotNull(responseQuestions);
        assertEquals(2, responseQuestions.size());
        
        QuestionDTO question1 = responseQuestions.get(0);
        assertEquals(1L, question1.getQuestionId());
        assertEquals("Question 1", question1.getQuestionTitle());
        assertEquals("A", question1.getOption1());
        assertEquals("B", question1.getOption2());
        assertEquals("C", question1.getOption3());
        assertEquals("D", question1.getOption4());
        assertEquals("OptionB", question1.getCorrectOption());
        assertEquals(4L, question1.getQuizId());
        
        QuestionDTO question2 = responseQuestions.get(1);
        assertEquals(2L, question2.getQuestionId());
        assertEquals("Question 2", question2.getQuestionTitle());
        assertEquals("A", question2.getOption1());
        assertEquals("B", question2.getOption2());
        assertEquals("C", question2.getOption3());
        assertEquals("D", question2.getOption4());
        assertEquals("OptionB", question2.getCorrectOption());
        assertEquals(5L, question2.getQuizId());
    }
}
