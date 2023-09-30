package com.capstoneproject.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.capstoneproject.response.Response;
import com.capstoneproject.response.SuccessMessages;
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
     * Creating instance for the Logger.
     */
    private Logger logger = LoggerFactory.getLogger(QuestionController.class);

    /**
     * Gets all the questions.
     * @return the List of the questions.
     */
    @GetMapping
    public final ResponseEntity<List<QuestionDTO>> getQuestions() {
        List<QuestionDTO> questions = questionService.getQuestions();
        logger.info(SuccessMessages.QUESTION_FETCH);
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
        logger.info(SuccessMessages.QUESTION_FETCH_BY_ID);
        return ResponseEntity.ok(newQuestion);
    }

    /**
     * adds the questions.
     * @param questionDto of Question type to be requested.
     * @return the success status when added.
     */
    @PostMapping
    public final ResponseEntity<Response> addQuestion(
            @RequestBody @Valid final QuestionDTO questionDto) {
        questionService.addQuestion(questionDto);
        logger.info(SuccessMessages.QUESTION_ADD_SUCCESS);
        Response response = new Response(HttpStatus.CREATED.value(),
                SuccessMessages.QUESTION_ADD_SUCCESS);
        return new ResponseEntity<Response>(response, HttpStatus.CREATED);
    }

    /**
     * Deletes the question.
     * @param questionId - It is needed to delete the Question of that id.
     * @return the deleted successfully status.
     */
    @DeleteMapping("/{questionId}")
    public final ResponseEntity<Response> deleteQuestion(
            @PathVariable final Long questionId) {
        questionService.deleteQuestion(questionId);
        logger.info(SuccessMessages.QUESTION_DELETE_SUCCESS);
        Response response = new Response(HttpStatus.NO_CONTENT.value(),
                SuccessMessages.QUESTION_DELETE_SUCCESS);
        return new ResponseEntity<Response>(response, HttpStatus.NO_CONTENT);
    }

    /**
     * updates the question.
     * @param questionId         is required to update its content.
     * @param updatedQuestionDto replace the content.
     * @return the success status after updating.
     */
    @PutMapping("/{questionId}")
    public final ResponseEntity<Response> updateQuestion(
            @PathVariable final Long questionId,
            @RequestBody @Valid final QuestionDTO updatedQuestionDto) {
        questionService.updateQuestion(questionId,
                updatedQuestionDto);
        logger.info(SuccessMessages.QUESTION_UPDATED_SUCCESS);
        Response response = new Response(HttpStatus.OK.value(),
                SuccessMessages.QUESTION_UPDATED_SUCCESS);
        return new ResponseEntity<Response>(response, HttpStatus.CREATED);
    }

    /**
     * This is used to get Question by Quiz Id.
     * @param quizId of Quiz.
     * @return the Response OK.
     */
    @GetMapping("/byQuiz/{quizId}")
    public final ResponseEntity<List<QuestionDTO>> getQuestionByQuizId(
            @PathVariable final Long quizId) {
        List<QuestionDTO> questionDto = questionService
                .getQuestionByQuizId(quizId);
        logger.info(SuccessMessages.QUESTION_FETCH_BY_QUIZ_ID);
        return ResponseEntity.ok(questionDto);
    }

}
