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

import com.capstoneproject.models.Category;
import com.capstoneproject.models.Quiz;
import com.capstoneproject.service.CategoryService;
import com.capstoneproject.service.QuizService;

/**
 * This class contains the Controller part of Quiz.
 */
@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/quiz")
public class QuizController {
    /**
     * quizService variable is used to perform the operations on Quiz Service.
     */
    @Autowired
    private QuizService quizService;
    /**
     * categoryService variable is used to operate on Category Service.
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * get all the Quiz.
     *
     * @return the List of the quiz.
     */
    @GetMapping
    public final ResponseEntity<List<Quiz>> getAllQuiz() {
        List<Quiz> quiz = quizService.getAllQuiz();
        return ResponseEntity.ok(quiz);
    }

    /**
     * Get the quiz based on ID.
     *
     * @param quizId of Long type as path variable.
     * @return the status whether the quiz exist or not.
     */
    @GetMapping("/{quizId}")
    public final ResponseEntity<Quiz> getQuizById(
            @PathVariable final Long quizId) {
        Quiz newQuiz = quizService.getQuizById(quizId);
        return ResponseEntity.ok(newQuiz);
    }

    /**
     * add quiz to the Repository.
     * @param quiz of Quiz type is requested.
     * @return the status whether the quiz is added or not.
     */
    @PostMapping
    public final ResponseEntity<Quiz> addQuiz(@RequestBody final Quiz quiz) {
        Quiz newQuiz = quizService.addQuiz(quiz);
        return ResponseEntity.ok(newQuiz);
    }

    /**
     * Deletes the quiz based on the id entered.
     * @param quizId of Long type is entered.
     * @return null if the quiz does not exist.
     */
    @DeleteMapping("/{quizId}")
    public final ResponseEntity<Void> deleteQuiz(
            @PathVariable final Long quizId) {
        quizService.deleteQuiz(quizId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Updates the quiz based on the id entered.
     *
     * @param quizId      of Long is considered to update that Quiz.
     * @param updatedQuiz to be updated.
     * @return the updated quiz.
     */
    @PutMapping("/{quizId}")
    public final ResponseEntity<Quiz> updateQuiz(
            @PathVariable final Long quizId,
            @RequestBody final Quiz updatedQuiz) {
        Quiz newQuiz = quizService.updateQuiz(quizId, updatedQuiz);
        return ResponseEntity.ok(newQuiz);
    }

    /**
     * Gets quiz based on the entered categoryId.
     *
     * @param categoryId is to be entered to get the quiz associated.
     * @return the quiz of that Category Id if found.
     */
    @GetMapping("/byCategory/{categoryId}")
    public final ResponseEntity<List<Quiz>> getQuizByCategoryId(
            @PathVariable final Long categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        List<Quiz> newQuiz = quizService.getQuizByCategoryId(category);
        return ResponseEntity.ok(newQuiz);
    }
}
