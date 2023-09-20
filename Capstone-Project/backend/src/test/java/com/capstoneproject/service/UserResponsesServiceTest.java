package com.capstoneproject.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capstoneproject.dto.UserResponsesDTO;
import com.capstoneproject.exceptions.AlreadyExistsException;
import com.capstoneproject.exceptions.ElementNotExistsException;
import com.capstoneproject.exceptions.NoInputException;
import com.capstoneproject.models.Category;
import com.capstoneproject.models.Quiz;
import com.capstoneproject.models.User;
import com.capstoneproject.models.UserResponses;
import com.capstoneproject.repository.AllResultsRepository;
import com.capstoneproject.repository.CategoryRepository;
import com.capstoneproject.repository.QuizRepository;
import com.capstoneproject.repository.UserRepository;
import com.capstoneproject.repository.UserResponsesRepository;

class UserResponsesServiceTest {

    @Mock
    private UserResponsesRepository userResponsesRepo;

    @Mock
    private UserRepository userRepo;

    @Mock
    private QuizRepository quizRepo;

    @Mock
    private CategoryRepository categoryRepo;
    
    @Mock
    private AllResultsRepository allResultsRepo;

    @InjectMocks
    private UserResponsesService userResponsesService; 

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddUserResponses() {
        UserResponsesDTO userResponsesDto = new UserResponsesDTO();
        userResponsesDto.setUserId(5L);
        userResponsesDto.setQuizId(6L);
        userResponsesDto.setCategoryId(7L);
        userResponsesDto.setNumOfQuestions(10);
        userResponsesDto.setNumOfQuestionsAnswered(8);
        userResponsesDto.setTotalMarks(20);
        userResponsesDto.setMarksScored(16);
        
        User user = new User(5L, "Yateesh");
        Category category = new Category(7L, "Category Test", "This is test Description");
        Quiz quiz = new Quiz(6L, "Quiz Test", "Test Quiz Description", 10, 5);
        when(userRepo.findById(5L)).thenReturn(Optional.of(user));
        when(quizRepo.findById(quiz.getQuizId())).thenReturn(Optional.of(quiz));
        when(categoryRepo.findById(category.getCategoryId())).thenReturn(Optional.of(category));
        when(userResponsesRepo.findResponsesByUsersAndQuiz(user.getUserId(), quiz.getQuizId())).thenReturn(null);

        UserResponsesDTO addedResponses = userResponsesService.addUserResponses(userResponsesDto);
        assertNotNull(addedResponses);
        assertEquals(10, addedResponses.getNumOfQuestions());
        assertEquals(8, addedResponses.getNumOfQuestionsAnswered());
    }

    @Test
    public void testAddResponses_NoInputException() {
        UserResponsesDTO userResponsesDto = new UserResponsesDTO();
        assertThrows(NoInputException.class, () -> userResponsesService.addUserResponses(userResponsesDto));
    }

    @Test
    public void testAddUserResponses_AlreadyExistsException() {
        UserResponsesDTO userResponsesDto = new UserResponsesDTO();
        userResponsesDto.setUserId(1L);
        userResponsesDto.setQuizId(3L);
        UserResponses existingResponses = new UserResponses();
        when(userResponsesRepo.findResponsesByUsersAndQuiz(1L, 3L)).thenReturn(existingResponses);
        assertThrows(NoInputException.class, () -> userResponsesService.addUserResponses(userResponsesDto));
    }

    @Test
    public void testAddUserResponsesWithExistingResponse() {
        UserResponsesDTO userResponsesDto = new UserResponsesDTO();
        userResponsesDto.setUserId(1L);
        userResponsesDto.setCategoryId(3L);
        userResponsesDto.setQuizId(5L);
        userResponsesDto.setTotalMarks(10);
        userResponsesDto.setMarksScored(8);
        userResponsesDto.setNumOfQuestions(10);
        userResponsesDto.setNumOfQuestionsAnswered(8);
        when(userResponsesRepo.findResponsesByUsersAndQuiz(1L, 5L)).thenReturn(new UserResponses());
        assertThrows(AlreadyExistsException.class, () -> userResponsesService.addUserResponses(userResponsesDto));
    }
    
    @Test
    public void testAddUserResponsesWithNonExistingUser() {
        UserResponsesDTO userResponsesDto = new UserResponsesDTO();
        userResponsesDto.setUserId(1L);
        userResponsesDto.setCategoryId(3L);
        userResponsesDto.setQuizId(5L);
        userResponsesDto.setTotalMarks(10);
        userResponsesDto.setMarksScored(8);
        userResponsesDto.setNumOfQuestions(10);
        userResponsesDto.setNumOfQuestionsAnswered(8);
        when(userResponsesRepo.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ElementNotExistsException.class, () -> userResponsesService.addUserResponses(userResponsesDto));
    }

    @Test
    public void testAddUserResponsesWithNonExistingQuiz() {
        UserResponsesDTO userResponsesDto = new UserResponsesDTO();
        userResponsesDto.setUserId(1L);
        userResponsesDto.setCategoryId(3L);
        userResponsesDto.setQuizId(5L);
        userResponsesDto.setTotalMarks(10);
        userResponsesDto.setMarksScored(8);
        userResponsesDto.setNumOfQuestions(10);
        userResponsesDto.setNumOfQuestionsAnswered(8);
        when(userRepo.findById(1L)).thenReturn(Optional.of(new User()));
        when(quizRepo.findById(5L)).thenReturn(Optional.empty());
        assertThrows(ElementNotExistsException.class, () -> userResponsesService.addUserResponses(userResponsesDto));
    }

    @Test
    public void testFindUserResponsesByUserAndQuiz() {
        when(userResponsesRepo.findResponsesByUsersAndQuiz(1L, 3L)).thenReturn(new UserResponses());
        boolean result = userResponsesService.findUserResponsesByUserAndQuiz(1L, 3L);
        assertTrue(result);
    }
    
    @Test
    public void testFindUserResponsesByUserAndQuizNoInput() {
        assertThrows(NoInputException.class, () -> userResponsesService.findUserResponsesByUserAndQuiz(1L, null));
        assertThrows(NoInputException.class, () -> userResponsesService.findUserResponsesByUserAndQuiz(null, 3L));
    }

    @Test
    public void testFindUserResponsesByUserAndQuizNonExistingUser() {
        when(userResponsesRepo.findResponsesByUsersAndQuiz(1L, 3L)).thenReturn(null);
        boolean result = userResponsesService.findUserResponsesByUserAndQuiz(1L, 3L);
        assertFalse(result);
    }
}
