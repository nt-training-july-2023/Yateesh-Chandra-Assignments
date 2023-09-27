package com.capstoneproject.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstoneproject.dto.QuestionDTO;
import com.capstoneproject.exceptions.AlreadyExistsException;
import com.capstoneproject.exceptions.ConflictException;
import com.capstoneproject.exceptions.ElementNotExistsException;
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
    public final List<QuestionDTO> getQuestions() {
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

        Quiz quiz = quizRepository.findById(
                questionDTO.getQuizId()).orElseThrow(
                        () -> new ElementNotExistsException("No Quiz found with Id : " + questionDTO.getQuizId()));
        
        Set<String> optionList = new HashSet<>();
        optionList.add(questionDTO.getOption1());
        optionList.add(questionDTO.getOption2());
        optionList.add(questionDTO.getOption3());
        optionList.add(questionDTO.getOption4());
        final int optionNumber = 4;
        if(optionList.size() < optionNumber) {
            throw new AlreadyExistsException("Options must not be repeated");
        }
        Question newQuestion = new Question();
        newQuestion.setQuestionId(questionDTO.getQuestionId());
        newQuestion.setQuestionTitle(questionDTO.getQuestionTitle());
        newQuestion.setOption1(questionDTO.getOption1());
        newQuestion.setOption2(questionDTO.getOption2());
        newQuestion.setOption3(questionDTO.getOption3());
        newQuestion.setOption4(questionDTO.getOption4());
        boolean matchFound = false;
        for (String option : optionList) {
            if(questionDTO.getCorrectOption().equalsIgnoreCase(option)) {
                newQuestion.setCorrectOption(questionDTO.getCorrectOption());
                matchFound = true;
            } 
        }
        if(!matchFound) {
            throw new ConflictException("Option does not match any option");
        }
        newQuestion.setQuiz(quiz);
        questionRepository.save(newQuestion);
        return questionDTO;
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
                .orElseThrow(() -> new ElementNotExistsException("No question found with that Id"));
        existingQuestion
                    .setQuestionTitle(updatedQuestionDTO.getQuestionTitle());
        Set<String> optionList = new HashSet<>();
        optionList.add(updatedQuestionDTO.getOption1());
        optionList.add(updatedQuestionDTO.getOption2());
        optionList.add(updatedQuestionDTO.getOption3());
        optionList.add(updatedQuestionDTO.getOption4());
        final int optionNumber = 4;
        if(optionList.size() < optionNumber) {
            throw new AlreadyExistsException("Options must not be repeated");
        }
        existingQuestion.setOption1(updatedQuestionDTO.getOption1());
        existingQuestion.setOption2(updatedQuestionDTO.getOption2());
        existingQuestion.setOption3(updatedQuestionDTO.getOption3());
        existingQuestion.setOption4(updatedQuestionDTO.getOption4());
        boolean matchFound = false;
        for (String option : optionList) {
            if(updatedQuestionDTO.getCorrectOption().equalsIgnoreCase(option)) {
                existingQuestion.setCorrectOption(updatedQuestionDTO.getCorrectOption());
                matchFound = true;
            } 
        }
        if(!matchFound) {
            throw new ConflictException("Option does not match any option");
        }
        questionRepository.save(existingQuestion);
        return updatedQuestionDTO;
    }

    /**
     * deletes the question by Id.
     *
     * @param questionId of Long type.
     */
    public final void deleteQuestion(final Long questionId) {
       questionRepository.findById(questionId)
                .orElseThrow(() -> new ElementNotExistsException("No Question found with that Id"));
       questionRepository.deleteById(questionId);
    }

    /**
     * This method returns the Questions when Quiz ID is given.
     * @param quizId of Quiz.
     * @return the List Of questions.
     */
    public final List<QuestionDTO> getQuestionByQuizId(final Long quizId) {
        quizRepository.findById(quizId).orElseThrow(
                () -> new ElementNotExistsException("Quiz Not Found with that ID"));
        List<Question> questions = questionRepository
                    .getQuestionByQuizId(quizId);
        return questions.stream().map(this::convertModelToDTO)
                .collect(Collectors.toList());
    }
}
