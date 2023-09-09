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

import com.capstoneproject.dto.QuizDTO;
import com.capstoneproject.service.QuizService;

/**
 * This class contains the Controller part of Quiz.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/quiz")
public class QuizController {
    /**
     * quizService variable is used to perform the operations on Quiz Service.
     */
    @Autowired
    private QuizService quizService;

    /**
     * get all the Quiz.
     * @return the List of the quiz.
     */
    @GetMapping
    public final ResponseEntity<Object> getAllQuiz() {
        List<QuizDTO> quiz = quizService.getAllQuiz();
        return ResponseEntity.ok(quiz);
    }

    /**
     * Get the quiz based on ID.
     * @param quizId of Long type as path variable.
     * @return the status whether the quiz exist or not.
     */
    @GetMapping("/{quizId}")
    public final ResponseEntity<Object> getQuizById(
            @PathVariable final Long quizId) {
        QuizDTO newQuiz = quizService.getQuizById(quizId);
        return ResponseEntity.ok(newQuiz);
    }

    /**
     * add quiz to the Repository.
     * @param quizDto of Quiz type is requested.
     * @return newQuiz.
     */
    @PostMapping
    public final ResponseEntity<Object> addQuiz(
            @RequestBody final QuizDTO quizDto) {
        QuizDTO newQuiz = quizService.addQuiz(quizDto);
        return ResponseEntity.ok(newQuiz);
    }

    /**
     * Deletes the quiz based on the id entered.
     * @param quizId of Long type is entered.
     * @return null if the quiz does not exist.
     */
    @DeleteMapping("/{quizId}")
    public final ResponseEntity<Object> deleteQuiz(
            @PathVariable final Long quizId) {
        quizService.deleteQuiz(quizId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Updates the quiz based on the id entered.
     * @param quizId of Long is considered to update that Quiz.
     * @param updatedQuiz to be updated.
     * @return the updated quiz.
     */
    @PutMapping("/{quizId}")
    public final ResponseEntity<Object> updateQuiz(
            @PathVariable final Long quizId,
            @RequestBody final QuizDTO updatedQuiz) {
        QuizDTO newQuiz = quizService.updateQuiz(quizId, updatedQuiz);
        return ResponseEntity.ok(newQuiz);
    }

    /**
     * Gets quiz based on the entered categoryId.
     * @param categoryId is to be entered to get the quiz associated.
     * @return the quiz of that Category Id if found.
     */
    @GetMapping("/byCategory/{categoryId}")
    public final ResponseEntity<Object> getQuizByCategoryId(
            @PathVariable final Long categoryId) {
        List<QuizDTO> quizDto = quizService.getQuizByCategoryId(categoryId);
        return ResponseEntity.ok(quizDto);
    }
}
