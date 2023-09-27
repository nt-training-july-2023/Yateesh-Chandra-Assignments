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

import com.capstoneproject.dto.QuestionDTO;
import com.capstoneproject.service.QuestionService;

import jakarta.validation.Valid;

/**
 * This class contains the Controller part for the Question.
 */
@CrossOrigin
@RestController
@RequestMapping(path = "question")
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
    public final ResponseEntity<List<QuestionDTO>> getAllQuestions() {
        List<QuestionDTO> questions = questionService.getQuestions();
        return ResponseEntity.ok(questions);
    }

    /**
     * Gets the question for the Specific Id.
     * @param questionId of Long type for input.
     * @return the Question associated with a specific ID.
     */
    @GetMapping("/{questionId}")
    public final ResponseEntity<QuestionDTO> getQuestionById(
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
    public final ResponseEntity<String> addQuestion(
            @RequestBody @Valid final QuestionDTO questionDto) {
        questionService.addQuestion(questionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Question added");
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
        String message = "Question is Deleted";
        return ResponseEntity.noContent().header("Message", message).build();
    }

    /**
     * updates the question.
     * @param questionId         is required to update its content.
     * @param updatedQuestionDto replace the content.
     * @return the success status after updating.
     */
    @PutMapping("/{questionId}")
    public final ResponseEntity<String> updateQuestion(
            @PathVariable final Long questionId,
            @RequestBody @Valid final QuestionDTO updatedQuestionDto) {
        questionService.updateQuestion(questionId,
                updatedQuestionDto);
        return ResponseEntity.ok("Quiz is updated");
    }

    /**
     * This is used to get Question by Quiz Id.
     * @param quizId of Quiz.
     * @return the Response Ok.
     */
    @GetMapping("/byQuiz/{quizId}")
    public final ResponseEntity<List<QuestionDTO>> getQuestionByQuizId(
            @PathVariable final Long quizId) {
        List<QuestionDTO> questionDto = questionService
                .getQuestionByQuizId(quizId);
        return ResponseEntity.ok(questionDto);
    }

}
