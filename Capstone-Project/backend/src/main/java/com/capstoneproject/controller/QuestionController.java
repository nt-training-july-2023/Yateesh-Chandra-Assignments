package com.capstoneproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstoneproject.models.Question;
import com.capstoneproject.service.QuestionService;

/**
 * This class contains the Controller part for the Question.
 */
@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/question")
public class QuestionController {
    /**
     * questionService variable is used to operate on the Question Service.
     */
    @Autowired
    private QuestionService questionService;

    /**
     * Gets all the questions.
     * @return the List of the questions.
     */
    @GetMapping
    public final ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> question = questionService.getAllQuestions();
        return ResponseEntity.ok(question);
    }

    /**
     * Gets the question for the Specific Id.
     * @param questionId of Long type for input.
     * @return the Question associated with a specific ID.
     */
    @GetMapping("/{questionId}")
    public final ResponseEntity<Question> getQuestionById(
            @PathVariable final Long questionId) {
        Question newQuestion = questionService.getQuestionById(questionId);
        return ResponseEntity.ok(newQuestion);
    }

    /**
     * adds the questions.
     * @param question of Question type to be requested.
     * @return the success status when added.
     */
    @PostMapping
    public final ResponseEntity<Question> addQuestion(
            @RequestBody final Question question) {
        Question newQuestion = questionService.addQuestion(question);
        return ResponseEntity.ok(newQuestion);
    }

    /**
     * Deletes the question.
     * @param questionId - It is needed to delete the Question of that id.
     * @return the deleted successfully status.
     */
    @DeleteMapping("/{questionId}")
    public final ResponseEntity<Void> deleteQuestion(
            @PathVariable final Long questionId) {
        questionService.deleteQuestion(questionId);
        return ResponseEntity.notFound().build();
    }
    /**
     * updates the question.
     * @param questionId is required to update its content.
     * @param updatedQuestion replace the content.
     * @return the success status after updating.
     */
    @PutMapping("/{questionId}")
    public final ResponseEntity<Question> updateQuestion(
            @PathVariable final Long questionId,
            @RequestBody final Question updatedQuestion) {
        Question newQuestion = questionService.updateQuestion(questionId,
                updatedQuestion);
        return ResponseEntity.ok(newQuestion);
    }
    /**
     * get correct answer for the question based on the Id.
     * @param questionId is specified to get its correct answer.
     * @return updated if found or else not found.
     */
    @GetMapping("/{questionId}/answer")
    public final ResponseEntity<String> getCorrectAnswerForQuestion(
            @PathVariable final Long questionId) {
        System.out.println("Received Quesion Id : " + questionId);
        String correctAnswer = questionService
                .getCorrectAnswerForQuestion(questionId);
        System.out.println("Retrieved Correct answer : " + correctAnswer);
        if (correctAnswer != null) {
            return ResponseEntity.ok(correctAnswer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
