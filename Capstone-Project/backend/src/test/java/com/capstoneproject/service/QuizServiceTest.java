package com.capstoneproject.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capstoneproject.dto.QuizDTO;
import com.capstoneproject.exceptions.AlreadyExistsException;
import com.capstoneproject.exceptions.ElementNotExistsException;
import com.capstoneproject.exceptions.NoInputException;
import com.capstoneproject.models.Category;
import com.capstoneproject.models.Quiz;
import com.capstoneproject.repository.CategoryRepository;
import com.capstoneproject.repository.QuizRepository;

import jakarta.persistence.EntityNotFoundException;

public class QuizServiceTest {

    @Mock
    private QuizRepository quizRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private QuizService quizService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllQuiz() {
        List<Quiz> quizList = new ArrayList<>();
        quizList.add(new Quiz(1L, "Quiz 1", "Description 1", 5, new Category()));
        quizList.add(new Quiz(2L, "Quiz 2", "Description 2", 10, new Category()));
        when(quizRepository.findAll()).thenReturn(quizList);
        List<QuizDTO> quizDTOList = quizService.getAllQuiz();
        assertNotNull(quizDTOList);
        assertEquals(2, quizDTOList.size());
        assertEquals("Quiz 1", quizDTOList.get(0).getQuizName());
        assertEquals("Quiz 2", quizDTOList.get(1).getQuizName());
    }

    @Test
    public void testGetQuizByCategoryId() {
        Long categoryId = 1L;
        Category category = new Category(categoryId, "Category 1", "Category Description");
        List<Quiz> quizList = new ArrayList<>();
        quizList.add(new Quiz(1L, "Quiz 1", "Description 1", 5, category));
        quizList.add(new Quiz(2L, "Quiz 2", "Description 2", 10, category));
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        when(quizRepository.getQuizByCategoryId(categoryId)).thenReturn(quizList);
        List<QuizDTO> quizDTOList = quizService.getQuizByCategoryId(categoryId);
        assertNotNull(quizDTOList);
        assertEquals(2, quizDTOList.size());
        assertEquals("Quiz 1", quizDTOList.get(0).getQuizName());
        assertEquals("Quiz 2", quizDTOList.get(1).getQuizName());
    }

    @Test
    public void testGetQuizByCategoryIdElementNotExists() {
        Long categoryId = 1L;
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());
        assertThrows(ElementNotExistsException.class, () -> quizService.getQuizByCategoryId(categoryId));
    }

    @Test
    public void testGetQuizById() {
        Long quizId = 1L;
        Category category = new Category(1L, "Category 1", "Category Description");
        Quiz quiz = new Quiz(quizId, "Quiz 1", "Description 1", 5, category);
        when(quizRepository.findById(quizId)).thenReturn(Optional.of(quiz));
        QuizDTO quizDTO = quizService.getQuizById(quizId);
        assertNotNull(quizDTO);
        assertEquals("Quiz 1", quizDTO.getQuizName());
        assertEquals("Description 1", quizDTO.getQuizDescription());
        assertEquals(0, quizDTO.getNumOfQuestions());
    }

    @Test
    public void testGetQuizByIdElementNotExists() {
        Long quizId = 1L;
        when(quizRepository.findById(quizId)).thenReturn(Optional.empty());
        assertThrows(ElementNotExistsException.class, () -> quizService.getQuizById(quizId));
    }

    @Test
    public void testAddQuiz() {
        Long categoryId = 1L;
        QuizDTO quizDTO = new QuizDTO(null, "New Quiz", "New Description", 5, categoryId);
        Category category = new Category(categoryId, "Category 1", "Category Description");
        when(quizRepository.getQuizByName(quizDTO.getQuizName())).thenReturn(Optional.empty());
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        when(quizRepository.save(any(Quiz.class))).thenReturn(new Quiz(1L, "New Quiz", "New Description", 5, category));
        QuizDTO addedQuiz = quizService.addQuiz(quizDTO);
        assertNull(addedQuiz.getQuizId());
        assertEquals("New Quiz", addedQuiz.getQuizName());
        assertEquals("New Description", addedQuiz.getQuizDescription());
        assertEquals(categoryId, addedQuiz.getCategoryId());
    }

    @Test
    public void testAddQuizAlreadyExists() {
        Long categoryId = 1L;
        QuizDTO quizDTO = new QuizDTO(null, "Existing Quiz", "Description", 5, categoryId);
        Category category = new Category(categoryId, "Category 1", "Category Description");
        when(quizRepository.getQuizByName(quizDTO.getQuizName())).thenReturn(Optional.of(new Quiz()));
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        assertThrows(AlreadyExistsException.class, () -> quizService.addQuiz(quizDTO));
    }

    @Test
    public void testAddQuizInvalidInput() {
        Long categoryId = 1L;
        QuizDTO quizDTO = new QuizDTO(null, "", "Description", 5, categoryId);
        assertThrows(NoInputException.class, () -> quizService.addQuiz(quizDTO));
    }

    @Test
    public void testDeleteQuiz() {
        Long quizId = 1L;
        when(quizRepository.findById(quizId)).thenReturn(Optional.of(new Quiz()));
        quizService.deleteQuiz(quizId);
    }

    @Test
    public void testDeleteQuizElementNotExists() {
        Long quizId = 1L;
        when(quizRepository.findById(quizId)).thenReturn(Optional.empty());
        assertThrows(ElementNotExistsException.class, () -> quizService.deleteQuiz(quizId));
    }

    @Test
    public void testUpdateQuiz() {
        Long quizId = 1L;
        QuizDTO updatedQuizDTO = new QuizDTO(null, "Updated Quiz", "Updated Description", 10, 1L);
        Quiz existingQuiz = new Quiz(quizId, "Existing Quiz", "Description", 5, new Category());
        when(quizRepository.findById(quizId)).thenReturn(Optional.of(existingQuiz));
        when(quizRepository.save(any(Quiz.class))).thenReturn(existingQuiz);
        QuizDTO updatedQuiz = quizService.updateQuiz(quizId, updatedQuizDTO);
        assertNotNull(updatedQuiz);
        assertEquals("Updated Quiz", updatedQuiz.getQuizName());
        assertEquals("Updated Description", updatedQuiz.getQuizDescription());
        assertEquals(10, updatedQuiz.getNumOfQuestions());
    }

    @Test
    public void testUpdateQuizNotFound() {
        Long quizId = 1L;
        QuizDTO updatedQuizDTO = new QuizDTO(null, "Updated Quiz", "Updated Description", 10, 1L);
        when(quizRepository.findById(quizId)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> quizService.updateQuiz(quizId, updatedQuizDTO));
    }
}
