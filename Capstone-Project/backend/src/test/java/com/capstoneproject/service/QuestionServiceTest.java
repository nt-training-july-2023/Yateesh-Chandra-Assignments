package com.capstoneproject.service;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capstoneproject.dto.QuestionDTO;
import com.capstoneproject.exceptions.ElementNotExistsException;
import com.capstoneproject.exceptions.NoInputException;
import com.capstoneproject.models.Question;
import com.capstoneproject.models.Quiz;
import com.capstoneproject.repository.QuestionRepository;
import com.capstoneproject.repository.QuizRepository;

class QuestionServiceTest {

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private QuizRepository quizRepository;
    
    @InjectMocks
    private QuestionService questionService;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllQuestions() {
        
        Question q1= new Question("Test Question", "A", "B", "C", "D", "OptionB");
        Question q2= new Question("Test Question 2", "A1", "B2", "C3", "D4", "OptionC");
        Quiz quiz = new Quiz(2L, "Test QUiz", "Desc", 5, 2);
        q1.setQuiz(quiz);
        q2.setQuiz(quiz);
        List<Question> questionList = new ArrayList<>();
        questionList.add(q2);
        questionList.add(q1);
        when(questionRepository.findAll()).thenReturn(questionList);
        
        List<QuestionDTO> questionDtoList = questionService.getAllQuestions();
        assertEquals("Test Question", questionDtoList.get(1).getQuestionTitle());
        assertEquals("Test Question 2", questionDtoList.get(0).getQuestionTitle());
    }

    @Test
    public void testGetQuestionByQuizId() {
        Long quizId = 5L;
        Quiz quiz = new Quiz(quizId, "Quiz 1", "Description 1", 5, 2);
        Question q1= new Question("Test Question", "A", "B", "C", "D", "OptionB");
        Question q2= new Question("Test Question 2", "A1", "B2", "C3", "D4", "OptionC");
        q1.setQuiz(quiz);
        q2.setQuiz(quiz);
        List<Question> questionList = new ArrayList<>();
        questionList.add(q1);
        questionList.add(q2);
        when(quizRepository.findById(quizId)).thenReturn(Optional.of(quiz));
        when(questionRepository.getQuestionByQuizId(quizId)).thenReturn(questionList);
        List<QuestionDTO> questionDtoList = questionService.getQuestionByQuizId(quizId);
        assertNotNull(questionDtoList);
        assertEquals(2, questionDtoList.size());
        assertEquals("Test Question", questionDtoList.get(0).getQuestionTitle());
        assertEquals("Test Question 2", questionDtoList.get(1).getQuestionTitle());
    }

    @Test
    public void testGetQuestionByQuizIdElementNotExists() {
        Long quizId = 1L;
        when(quizRepository.findById(quizId)).thenReturn(Optional.empty());
        assertThrows(ElementNotExistsException.class, () -> questionService.getQuestionByQuizId(quizId));
    }
    
    @Test
    public void testGetQuestionById() {
        Long questionId = 10L;
        Quiz quiz = new Quiz(2L, "Test QUiz", "Desc", 5, 2);
        Question question = new Question("Test Question", "A", "B", "C", "D", "OptionB");
        question.setQuiz(quiz);
        when(questionRepository.findById(questionId)).thenReturn(Optional.of(question));
        QuestionDTO questionDto = questionService.getQuestionById(questionId);
        assertEquals("Test Question", questionDto.getQuestionTitle());
        assertEquals("A", questionDto.getOption1());
        assertEquals("B", questionDto.getOption2());
        assertEquals("C", questionDto.getOption3());
        assertEquals("D", questionDto.getOption4());
        assertEquals("OptionB", questionDto.getCorrectOption());
    }
    
    @Test
    public void testQuestionByIdElementNotExists() {
        Long questionId = 1L;
        when(questionRepository.findById(questionId)).thenReturn(Optional.empty());
        assertThrows(ElementNotExistsException.class, () -> questionService.getQuestionById(questionId));
    }
    
    @Test
    public void testAddQuestion() {
        Long quizId = 1L;
        QuestionDTO questionDto = new QuestionDTO(null, "Test Question", "A", "B", "C", "D", "OptionB", quizId);
        Quiz quiz = new Quiz(quizId, "Quiz 1", "Quiz 1 Description", 7, 2);
        when(quizRepository.findById(quizId)).thenReturn(Optional.of(quiz));
        when(questionRepository.save(any(Question.class))).thenReturn(new Question("Test Question", "A", "B", "C", "D", "OptionB"));
        QuestionDTO addedQuestion = questionService.addQuestion(questionDto);
        assertNull(addedQuestion.getQuestionId());
        assertEquals("Test Question", addedQuestion.getQuestionTitle());
        assertEquals("A", addedQuestion.getOption1());
        assertEquals("B", addedQuestion.getOption2());
        assertEquals("C", addedQuestion.getOption3());
        assertEquals("D", addedQuestion.getOption4());
        assertEquals(quizId, addedQuestion.getQuizId());
    }
    
    @Test
    public void testAddQuestionInvalidInput() {
        Long quizId = 8L;
        QuestionDTO questionDto = new QuestionDTO(null, "", "A", "B", "C", "D", "OptionB", quizId);
        assertThrows(NoInputException.class, () -> questionService.addQuestion(questionDto));
    }
    
    @Test
    public void testAddQuestionEmptyFields() {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setQuestionTitle("What is 2 + 2?");
        questionDTO.setOption1("");
        questionDTO.setOption2("4");
        questionDTO.setOption3("5");
        questionDTO.setOption4("");
        questionDTO.setCorrectOption("4");
        questionDTO.setQuizId(1L);
        when(quizRepository.findById(questionDTO.getQuizId())).thenReturn(Optional.of(new Quiz()));
        assertThrows(NoInputException.class, () -> questionService.addQuestion(questionDTO));
    }

    @Test
    public void testAddQuestionQuizNotFound() {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setQuestionTitle("What is 2 + 2?");
        questionDTO.setOption1("3");
        questionDTO.setOption2("4");
        questionDTO.setOption3("5");
        questionDTO.setOption4("6");
        questionDTO.setCorrectOption("4");
        questionDTO.setQuizId(7L);
        when(quizRepository.findById(questionDTO.getQuizId())).thenReturn(Optional.empty());
        assertThrows(ElementNotExistsException.class, () -> questionService.addQuestion(questionDTO));
    }

    @Test
    public void testDeleteQuestion() {
        Long questionId = 5L;
        when(questionRepository.findById(questionId)).thenReturn(Optional.of(new Question()));
        questionService.deleteQuestion(questionId);
    }
    
    @Test
    public void testDeleteQuestionElementNotExists() {
        Long questionId = 5L;
        when(questionRepository.findById(questionId)).thenReturn(Optional.empty());
        assertThrows(ElementNotExistsException.class, () -> questionService.deleteQuestion(questionId));
    }
    
    @Test
    public void testUpdateQuiz() {
        Long questionId = 1L;
        QuestionDTO updatedQuestionDTO = new QuestionDTO(null, "Hello", "A", "B", "C", "D", "OptionB", 1L);
        Question existingQuestion = new Question("Hello", "A", "B", "C", "D", "OptionB");
        when(questionRepository.findById(questionId)).thenReturn(Optional.of(existingQuestion));
        when(questionRepository.save(any(Question.class))).thenReturn(existingQuestion);
        QuestionDTO updatedQuestion = questionService.updateQuestion(questionId, updatedQuestionDTO);
        assertNotNull(updatedQuestion);
        assertEquals("Hello", updatedQuestion.getQuestionTitle());
        assertEquals("A", updatedQuestion.getOption1());
        assertEquals("B", updatedQuestion.getOption2());
        assertEquals("C", updatedQuestion.getOption3());
        assertEquals("D", updatedQuestion.getOption4());
        assertEquals("OptionB", updatedQuestion.getCorrectOption());
    }
    
    @Test
    public void testUpdateQuizNotFound() {
        Long questionId = 7L;
        QuestionDTO questionDto = new QuestionDTO(null, "Hello", "A", "B", "C", "D", "OptionB", 1L);
        when(questionRepository.findById(questionId)).thenReturn(Optional.empty());
        assertThrows(ElementNotExistsException.class, () -> questionService.updateQuestion(questionId, questionDto));
    }
    
    @Test
    public void testUpdateQuizWithElementNotFoundException() {
        Long questionId = 5L;
        QuestionDTO questionDto = new QuestionDTO();
        when(questionRepository.findById(questionId)).thenReturn(Optional.empty());
        assertThrows(ElementNotExistsException.class, () -> questionService.updateQuestion(questionId, questionDto));
    }

}
