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

import com.capstoneproject.dto.QuestionDTO;
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
    public final ResponseEntity<Object> getAllQuestions() {
        List<QuestionDTO> question = questionService.getAllQuestions();
        return ResponseEntity.ok(question);
    }

    /**
     * Gets the question for the Specific Id.
     * @param questionId of Long type for input.
     * @return the Question associated with a specific ID.
     */
    @GetMapping("/{questionId}")
    public final ResponseEntity<Object> getQuestionById(
            @PathVariable final Long questionId) {
        QuestionDTO newQuestion = questionService.getQuestionById(questionId);
        return ResponseEntity.ok(newQuestion);
    }

    /**
     * adds the questions.
     * @param questionDto of Question type to be requested.
     * @return the success status when added.
     */
    @PostMapping
    public final ResponseEntity<Object> addQuestion(
            @RequestBody final QuestionDTO questionDto) {
        QuestionDTO newQuestion = questionService.addQuestion(questionDto);
        return ResponseEntity.ok(newQuestion);
    }

    /**
     * Deletes the question.
     * @param questionId - It is needed to delete the Question of that id.
     * @return the deleted successfully status.
     */
    @DeleteMapping("/{questionId}")
    public final ResponseEntity<Object> deleteQuestion(
            @PathVariable final Long questionId) {
        questionService.deleteQuestion(questionId);
        return ResponseEntity.notFound().build();
    }

    /**
     * updates the question.
     * @param questionId         is required to update its content.
     * @param updatedQuestionDto replace the content.
     * @return the success status after updating.
     */
    @PutMapping("/{questionId}")
    public final ResponseEntity<Object> updateQuestion(
            @PathVariable final Long questionId,
            @RequestBody final QuestionDTO updatedQuestionDto) {
        QuestionDTO newQuestion = questionService.updateQuestion(questionId,
                updatedQuestionDto);
        return ResponseEntity.ok(newQuestion);
    }

    /**
     * This is used to get Question by Quiz Id.
     * @param quizId of Quiz.
     * @return the Response Ok.
     */
    @GetMapping("/byQuiz/{quizId}")
    public final ResponseEntity<Object> getQuestionByQuizId(
            @PathVariable final Long quizId) {
        List<QuestionDTO> questionDto = questionService
                .getQuestionByQuizId(quizId);
        return ResponseEntity.ok(questionDto);
    }

}
