package com.capstoneproject.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstoneproject.dto.QuizDTO;
import com.capstoneproject.exceptions.AlreadyExistsException;
import com.capstoneproject.exceptions.ElementNotExistsException;
import com.capstoneproject.exceptions.NoInputException;
import com.capstoneproject.models.Category;
import com.capstoneproject.models.Quiz;
import com.capstoneproject.repository.CategoryRepository;
import com.capstoneproject.repository.QuizRepository;

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
     * Category Repository is autowired.
     */
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * This method gets all the quiz.
     * @return the List of Quiz.
     */
    public final List<QuizDTO> getAllQuiz() {
        List<Quiz> quizzes = quizRepository.findAll();
        return quizzes.stream().map(this::convertModelToDTO)
                .collect(Collectors.toList());
    }

    /**
     * This is used to convert the Entity to DTO.
     * @param quiz of Quiz type.
     * @return the Quiz DTO.
     */
    public final QuizDTO convertModelToDTO(final Quiz quiz) {
        QuizDTO quizDto = new QuizDTO();
        quizDto.setQuizId(quiz.getQuizId());
        quizDto.setQuizName(quiz.getQuizName());
        quizDto.setQuizDescription(quiz.getQuizDescription());
        quizDto.setNumOfQuestions(quiz.getNumOfQuestions());
        quizDto.setCategoryId(quiz.getCategory().getCategoryId());
        return quizDto;
    }

    /**
     * Gets the quiz based on the Category Id.
     * @param categoryId - of Category Type.
     * @return the List of Quiz by mentioned Category.
     */
    public final List<QuizDTO> getQuizByCategoryId(final Long categoryId) {
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElse(null);
        if (existingCategory == null) {
            throw new ElementNotExistsException();
        } else {
            List<Quiz> quizzes = quizRepository.getQuizByCategoryId(categoryId);
            return quizzes.stream().map(this::convertModelToDTO)
                    .collect(Collectors.toList());
        }
    }

    /**
     * Finds the Quiz.
     * @param quizId - of Long Data type.
     * @return the quiz by the specific ID.
     */
    public final QuizDTO getQuizById(final Long quizId) {
        Quiz existingQuiz = quizRepository.findById(quizId).orElseThrow(
                () -> new ElementNotExistsException("No quiz with the Id"));
        QuizDTO quizDto = new QuizDTO();
        quizDto.setQuizId(existingQuiz.getQuizId());
        quizDto.setQuizName(existingQuiz.getQuizName());
        quizDto.setQuizDescription(existingQuiz.getQuizDescription());
        quizDto.setNumOfQuestions(existingQuiz.getNumOfQuestions());
        quizDto.setCategoryId(existingQuiz.getCategory().getCategoryId());
        return quizDto;
    }

    /**
     * adds the quiz.
     * @param quizDto - of QuizDTO Type.
     * @return the quiz being added.
     */
    public final QuizDTO addQuiz(final QuizDTO quizDto) {
        if (quizDto.getQuizName().isEmpty() || quizDto.getCategoryId() == 0) {
            throw new NoInputException();
        } else {
            Optional<Quiz> existingQuiz = quizRepository
                    .getQuizByName(quizDto.getQuizName());
            if (existingQuiz.isPresent()) {
                throw new AlreadyExistsException("Quiz already Exists");
            } else {
                Quiz newQuiz = new Quiz();
                newQuiz.setQuizId(quizDto.getQuizId());
                newQuiz.setQuizName(quizDto.getQuizName());
                newQuiz.setQuizDescription(quizDto.getQuizDescription());
                newQuiz.setNumOfQuestions(quizDto.getNumOfQuestions());
                Category category = categoryRepository
                        .findById(quizDto.getCategoryId())
                        .orElseThrow(() -> new ElementNotExistsException(
                                "Category not found."));
                newQuiz.setCategory(category);
                quizRepository.save(newQuiz);
                return quizDto;
            }
        }
    }

    /**
     * deletes the quiz based on the Quiz Id.
     * @param quizId - of Long Type.
     */
    public final void deleteQuiz(final Long quizId) {
        Quiz existingQuiz = quizRepository.findById(quizId).orElse(null);
        if (existingQuiz == null) {
            throw new ElementNotExistsException();
        } else {
            quizRepository.deleteById(quizId);
        }
    }

    /**
     * updates the quiz based on the Id.
     * @param quizId  - Of Long Type.
     * @param quizDto - of Quiz Type.
     * @return status of the updated quiz.
     */
    public final QuizDTO updateQuiz(final Long quizId, final QuizDTO quizDto) {
        Quiz existingQuiz = quizRepository.findById(quizId).orElse(null);
        if (existingQuiz != null) {
            if (quizDto.getQuizName().isEmpty()) {
                throw new NoInputException();
            }
            Optional<Quiz> quiz = quizRepository
                    .getQuizByName(quizDto.getQuizName());
            if (quiz.isPresent() && !quiz.get().getQuizId().equals(quizId)) {
                throw new AlreadyExistsException("Quiz already exists");
            }
            existingQuiz.setQuizName(quizDto.getQuizName());
            existingQuiz.setQuizDescription(quizDto.getQuizDescription());
            existingQuiz.setNumOfQuestions(quizDto.getNumOfQuestions());
            Quiz newQuiz = quizRepository.save(existingQuiz);
            QuizDTO newQuizDTO = new QuizDTO();
            newQuizDTO.setQuizId(newQuiz.getQuizId());
            newQuizDTO.setQuizName(newQuiz.getQuizName());
            newQuizDTO.setQuizDescription(newQuiz.getQuizDescription());
            newQuizDTO.setNumOfQuestions(newQuiz.getNumOfQuestions());
            return newQuizDTO;
        } else {
            throw new ElementNotExistsException("Quiz Not found");
        }
    }
}
