package com.capstoneproject.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstoneproject.dto.UserResponsesDTO;
import com.capstoneproject.exceptions.ElementNotExistsException;
import com.capstoneproject.exceptions.NoInputException;
import com.capstoneproject.models.AllResults;
import com.capstoneproject.models.Category;
import com.capstoneproject.models.Quiz;
import com.capstoneproject.models.User;
import com.capstoneproject.models.UserResponses;
import com.capstoneproject.repository.AllResultsRepository;
import com.capstoneproject.repository.CategoryRepository;
import com.capstoneproject.repository.QuizRepository;
import com.capstoneproject.repository.UserRepository;
import com.capstoneproject.repository.UserResponsesRepository;

/**
 * This is Service class for the UserResponses.
 */
@Service
public class UserResponsesService {

    /**
     * This autowires the UserResponse Repository.
     */
    @Autowired
    private UserResponsesRepository responsesRepository;

    /**
     * This autowires AllResults Repository.
     */
    @Autowired
    private AllResultsRepository allResultsRepository;

    /**
     * This autowires the User Repository.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * This autowires the Category Repository.
     */
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * This autowires the Quiz Repository.
     */
    @Autowired
    private QuizRepository quizRepository;

    /**
     * This method is used to add the User Responses.
     * @param responses of UserResponsesDTO is passed.
     * @return the added User Response.
     */
    public final UserResponsesDTO addUserResponses(
            final UserResponsesDTO responses) {
        if (responses.getUserId() == null || responses.getCategoryId() == null
                || responses.getQuizId() == null
                || responses.getTotalMarks() == 0
                || responses.getNumOfQuestions() == 0) {
            throw new NoInputException("No Inputs Detected");
        } else {
            User user = userRepository.findById(responses.getUserId())
                    .orElse(null);
            if (user == null) {
                throw new ElementNotExistsException("This element not exists");
            } else {
                Quiz quiz = quizRepository.findById(responses.getQuizId())
                        .orElse(null);
                if (quiz != null) {
                    AllResults results = new AllResults();
                    results.setUserId(responses.getUserId());
                    Optional<User> users = userRepository
                            .findById(responses.getUserId());
                    results.setEmail(users.get().getEmail());
                    results.setUserName(users.get().getName());
                    Optional<Quiz> quizzes = quizRepository
                            .findById(responses.getQuizId());
                    Optional<Category> category = categoryRepository
                            .findById(responses.getCategoryId());
                    results.setCategoryName(category.get().getCategoryName());
                    results.setQuizName(quizzes.get().getQuizName());
                    results.setNumOfQuestions(responses.getNumOfQuestions());
                    results.setNumOfQuestionsAnswered(
                            responses.getNumOfQuestionsAnswered());
                    results.setTotalMarks(responses.getTotalMarks());
                    results.setMarksScored(responses.getMarksScored());
                    results.setTimeStamp(responses.setTimeStampMethod());
                    allResultsRepository.save(results);

                    UserResponses userResponses = new UserResponses();
                    userResponses.setUsers(user);
                    userResponses.setQuiz(quiz);
                    userResponses
                            .setNumOfQuestions(results.getNumOfQuestions());
                    userResponses.setNumOfQuestionsAnswered(
                            results.getNumOfQuestionsAnswered());
                    userResponses.setTotalMarks(responses.getTotalMarks());
                    userResponses.setMarksScored(responses.getMarksScored());
                    userResponses.setTimeStamp(responses.setTimeStampMethod());
                    responsesRepository.save(userResponses);
                    return responses;
                } else {
                    throw new ElementNotExistsException(
                            "No such element found");
                }
            }
        }
    }
}
