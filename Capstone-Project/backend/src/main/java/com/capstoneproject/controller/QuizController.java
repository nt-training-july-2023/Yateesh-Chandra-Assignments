package com.capstoneproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import jakarta.validation.Valid;

/**
 * This class contains the Controller part of Quiz.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "quiz")
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
    public final ResponseEntity<List<QuizDTO>> getAllQuiz() {
        List<QuizDTO> quiz = quizService.getQuizzes();
        return ResponseEntity.ok(quiz);
    }

    /**
     * Get the quiz based on ID.
     * @param quizId of Long type as path variable.
     * @return the status whether the quiz exist or not.
     */
    @GetMapping("/{quizId}")
    public final ResponseEntity<QuizDTO> getQuizById(
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
    public final ResponseEntity<String> addQuiz(
            @RequestBody @Valid final QuizDTO quizDto) {
        quizService.addQuiz(quizDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Quiz is Successfully Created");
    }

    /**
     * Deletes the quiz based on the id entered.
     * @param quizId of Long type is entered.
     * @return null if the quiz does not exist.
     */
    @DeleteMapping("/{quizId}")
    public final ResponseEntity<String> deleteQuiz(
            @PathVariable final Long quizId) {
        quizService.deleteQuiz(quizId);
        String message = "Quiz is deleted"; 
        return ResponseEntity.noContent().header("Message",message).build();
    }

    /**
     * Updates the quiz based on the id entered.
     * @param quizId of Long is considered to update that Quiz.
     * @param updatedQuiz to be updated.
     * @return the updated quiz.
     */
    @PutMapping("/{quizId}")
    public final ResponseEntity<String> updateQuiz(
            @PathVariable final Long quizId,
            @RequestBody @Valid final QuizDTO updatedQuiz) {
        quizService.updateQuiz(quizId, updatedQuiz);
        return ResponseEntity.ok("Quiz is updated");
    }

    /**
     * Gets quiz based on the entered categoryId.
     * @param categoryId is to be entered to get the quiz associated.
     * @return the quiz of that Category Id if found.
     */
    @GetMapping("/byCategory/{categoryId}")
    public final ResponseEntity<List<QuizDTO>> getQuizByCategoryId(
            @PathVariable final Long categoryId) {
        List<QuizDTO> quizDto = quizService.getQuizByCategoryId(categoryId);
        return ResponseEntity.ok(quizDto);
    }
}
