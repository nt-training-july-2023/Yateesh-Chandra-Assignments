package com.capstoneproject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstoneproject.dto.QuestionDTO;
import com.capstoneproject.exceptions.ElementNotExistsException;
import com.capstoneproject.exceptions.NoInputException;
import com.capstoneproject.models.Question;
import com.capstoneproject.models.Quiz;
import com.capstoneproject.repository.QuestionRepository;
import com.capstoneproject.repository.QuizRepository;

/**
 * This class contains the Service for Question.
 */
@Service
public class QuestionService {
    /**
     * questionRepository variable is created to perform Operations.
     */
    @Autowired
    private QuestionRepository questionRepository;

    /**
     * quizRepository is autowired.
     */
    @Autowired
    private QuizRepository quizRepository;

    /**
     * Gets the List of All questions.
     *
     * @return List of Questions.
     */
    public final List<QuestionDTO> getAllQuestions() {
        List<Question> question = questionRepository.findAll();
        return question.stream().map(this::convertModelToDTO)
                .collect(Collectors.toList());
    }

    /**
     * This method is written to convert the model into DTO.
     * @param question of Question is passed.
     * @return the Question DTO.
     */
    public final QuestionDTO convertModelToDTO(final Question question) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setQuestionId(question.getQuestionId());
        questionDTO.setQuestionTitle(question.getQuestionTitle());
        questionDTO.setOption1(question.getOption1());
        questionDTO.setOption2(question.getOption2());
        questionDTO.setOption3(question.getOption3());
        questionDTO.setOption4(question.getOption4());
        questionDTO.setCorrectOption(question.getCorrectOption());
        questionDTO.setQuizId(question.getQuiz().getQuizId());
        return questionDTO;
    }

    /**
     * Gets the Question by Specific User ID.
     *
     * @param questionId - of Long Type.
     * @return the Question of UserId.
     */
    public final QuestionDTO getQuestionById(final Long questionId) {
        Question existingQuestion = questionRepository.findById(questionId)
                .orElseThrow(() -> new ElementNotExistsException(
                        "No Question with that Id"));
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setQuestionId(existingQuestion.getQuestionId());
        questionDTO.setQuestionTitle(existingQuestion.getQuestionTitle());
        questionDTO.setOption1(existingQuestion.getOption1());
        questionDTO.setOption2(existingQuestion.getOption2());
        questionDTO.setOption3(existingQuestion.getOption3());
        questionDTO.setOption4(existingQuestion.getOption4());
        questionDTO.setCorrectOption(existingQuestion.getCorrectOption());
        questionDTO.setQuizId(existingQuestion.getQuiz().getQuizId());
        return questionDTO;
    }

    /**
     * Add the questions.
     *
     * @param questionDTO of Question Type.
     * @return the status of the question being added.
     */
    public final QuestionDTO addQuestion(final QuestionDTO questionDTO) {
        if (questionDTO.getQuestionTitle().isEmpty()
                || questionDTO.getQuizId() == 0) {
            throw new NoInputException("No Inputs detected");
        } else {
            Question newQuestion = new Question();
            newQuestion.setQuestionId(questionDTO.getQuestionId());
            newQuestion.setQuestionTitle(questionDTO.getQuestionTitle());
            newQuestion.setOption1(questionDTO.getOption1());
            newQuestion.setOption2(questionDTO.getOption2());
            newQuestion.setOption3(questionDTO.getOption3());
            newQuestion.setOption4(questionDTO.getOption4());
            newQuestion.setCorrectOption(questionDTO.getCorrectOption());
            Quiz quiz = quizRepository.findById(questionDTO.getQuizId())
                    .orElseThrow(() -> new ElementNotExistsException(
                            "Quiz not found"));
            newQuestion.setQuiz(quiz);
            questionRepository.save(newQuestion);
            return questionDTO;
        }
    }

    /**
     * updates the question.
     *
     * @param questionId      of Long type.
     * @param updatedQuestionDTO of Question type.
     * @return the updated question.
     */
    public final QuestionDTO updateQuestion(final Long questionId,
            final QuestionDTO updatedQuestionDTO) {
        Question existingQuestion = questionRepository.findById(questionId)
                .orElse(null);
        if (existingQuestion != null) {
            existingQuestion
                    .setQuestionTitle(updatedQuestionDTO.getQuestionTitle());
            existingQuestion.setOption1(updatedQuestionDTO.getOption1());
            existingQuestion.setOption2(updatedQuestionDTO.getOption2());
            existingQuestion.setOption3(updatedQuestionDTO.getOption3());
            existingQuestion.setOption4(updatedQuestionDTO.getOption4());
            existingQuestion
                    .setCorrectOption(updatedQuestionDTO.getCorrectOption());
            questionRepository.save(existingQuestion);
            return updatedQuestionDTO;
        } else {
            throw new ElementNotExistsException("Question ID not found");
        }
    }

    /**
     * deletes the question by Id.
     *
     * @param questionId of Long type.
     */
    public final void deleteQuestion(final Long questionId) {
        Question existingQuestion = questionRepository.findById(questionId)
                .orElse(null);
        if (existingQuestion == null) {
            throw new ElementNotExistsException(
                    "No Question was found with that ID");
        } else {
            questionRepository.deleteById(questionId);
        }
    }

    /**
     * This method returns the Questions when Quiz ID is given.
     * @param quizId of Quiz.
     * @return the List Of questions.
     */
    public final List<QuestionDTO> getQuestionByQuizId(final Long quizId) {
        Quiz existingQuiz = quizRepository.findById(quizId).orElse(null);
        if (existingQuiz == null) {
            throw new ElementNotExistsException("Quiz not available");
        } else {
            List<Question> questions = questionRepository
                    .getQuestionByQuizId(quizId);
            return questions.stream().map(this::convertModelToDTO)
                    .collect(Collectors.toList());
        }
    }
}
