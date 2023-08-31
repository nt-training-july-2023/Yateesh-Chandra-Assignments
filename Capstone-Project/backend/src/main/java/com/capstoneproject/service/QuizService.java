package com.capstoneproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstoneproject.models.Category;
import com.capstoneproject.models.Quiz;
import com.capstoneproject.repository.QuizRepository;

import jakarta.persistence.EntityNotFoundException;

/**
 * This class works like Service.
 */
@Service
public class QuizService {
    /**
     * The quizRepository value is used to handle operations for Quiz
     * Repository.
     */
    @Autowired
    private QuizRepository quizRepository;

    /**
     * This method gets all the quiz.
     *
     * @return the List of Quiz.
     */
    public final List<Quiz> getAllQuiz() {
        return quizRepository.findAll();
    }

    /**
     * Gets the quiz based on the Category Id.
     *
     * @param category - of Category Type.
     * @return the List of Quiz by mentioned Category.
     */
    public final List<Quiz> getQuizByCategoryId(final Category category) {
        return quizRepository.findByCategory(category);
    }

    /**
     * Finds the Quiz.
     *
     * @param quizId - of Long Data type.
     * @return the quiz by the specific ID.
     */
    public final Quiz getQuizById(final Long quizId) {
        Quiz existingQuiz = quizRepository.findById(quizId).get();
        return existingQuiz;
    }

    /**
     * adds the quiz.
     *
     * @param quiz - of Quiz Type.
     * @return the quiz being added.
     */
    public final Quiz addQuiz(final Quiz quiz) {
        return quizRepository.save(quiz);
    }

    /**
     * deletes the quiz based on the Quiz Id.
     *
     * @param quizId - of Long Type.
     */
    public final void deleteQuiz(final Long quizId) {
        quizRepository.deleteById(quizId);
    }

    /**
     * updates the quiz based on the Id.
     *
     * @param quizId      - Of Long Type.
     * @param updatedQuiz - of Quiz Type.
     * @return status of the updated quiz.
     */
    public final Quiz updateQuiz(final Long quizId, final Quiz updatedQuiz) {
        Quiz existingQuiz = quizRepository.findById(quizId).orElseThrow(
                () -> new EntityNotFoundException("Quiz Not Found.!"));
        existingQuiz.setQuizName(updatedQuiz.getQuizName());
        existingQuiz.setQuizDescription(updatedQuiz.getQuizDescription());
        existingQuiz.setNumOfQuestions(updatedQuiz.getNumOfQuestions());
        existingQuiz.setCategory(updatedQuiz.getCategory());
        return quizRepository.save(existingQuiz);
    }
}
