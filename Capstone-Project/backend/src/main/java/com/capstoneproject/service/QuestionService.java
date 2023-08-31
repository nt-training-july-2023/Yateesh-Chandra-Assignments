package com.capstoneproject.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capstoneproject.models.Question;
import com.capstoneproject.repository.QuestionRepository;
import jakarta.persistence.EntityNotFoundException;

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
     * Gets the List of All questions.
     *
     * @return List of Questions.
     */
    public final List<Question> getAllQuestions() {
        List<Question> question = questionRepository.findAll();
        for (Question questions : question) {
            questions.updateCorrectAnswer();
        }
        return question;
    }

    /**
     * Gets the Question by Specific User ID.
     *
     * @param questionId - of Long Type.
     * @return the Question of UserId.
     */
    public final Question getQuestionById(final Long questionId) {
        try {
            Question existingQuestion = questionRepository.findById(questionId)
                    .get();
            existingQuestion.updateCorrectAnswer();
            return existingQuestion;
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException(e);
        }
    }

    /**
     * Add the questions.
     *
     * @param question of Question Type.
     * @return the status of the question being added.
     */
    public final Question addQuestion(final Question question) {
        question.updateCorrectAnswer();
        return questionRepository.save(question);
    }

    /**
     * updates the question.
     *
     * @param questionId      of Long type.
     * @param updatedQuestion of Question type.
     * @return the updated question.
     */
    public final Question updateQuestion(final Long questionId,
            final Question updatedQuestion) {
        Question existingQuestion = questionRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Question not found with the ID"));
        existingQuestion.setQuestionTitle(updatedQuestion.getQuestionTitle());
        existingQuestion.setOption1(updatedQuestion.getOption1());
        existingQuestion.setOption2(updatedQuestion.getOption2());
        existingQuestion.setOption3(updatedQuestion.getOption3());
        existingQuestion.setOption4(updatedQuestion.getOption4());
        existingQuestion.setCorrectOption(updatedQuestion.getCorrectOption());
        existingQuestion.updateCorrectAnswer();
        existingQuestion.setQuiz(updatedQuestion.getQuiz());
        existingQuestion.updateCorrectAnswer();
        return questionRepository.save(existingQuestion);
    }

    /**
     * deletes the question by Id.
     *
     * @param questionId of Long type.
     */
    public final void deleteQuestion(final Long questionId) {
        questionRepository.deleteById(questionId);
    }

    /**
     * gets Correct Answer for Question.
     *
     * @param questionId of Long type.
     * @return the Correct Answer.
     */
    public final String getCorrectAnswerForQuestion(final Long questionId) {
        Question question = questionRepository.findById(questionId)
                .orElse(null);
        if (question != null) {
            question.updateCorrectAnswer();
            return question.getCorrectAnswer();
        }
        return "question Not found";
    }
}
