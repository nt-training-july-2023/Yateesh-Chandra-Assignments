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
import com.capstoneproject.models.UserAssessment;
import com.capstoneproject.service.QuestionService;
import com.capstoneproject.service.UserAssessmentService;
/**
 * This is the Controller class for User assessment.
 */
@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/user-assessment")
public class UserAssessmentController {
  /**
   * userAssessment variable is created to operate on UserAssessmentService.
   */
  @Autowired
  private UserAssessmentService userAssessmentService;
  /**
   * The questionService variable is created to operate on QuestionService.
   */
  @Autowired
  private QuestionService questionService;
  /**
   * This method adds User Assessment.
   * @param userAssessment It is to request body and add.
   * @return the status of the UserAssessment is added successfully or not.
   */
  @PostMapping
  public final ResponseEntity<UserAssessment> addUserAssessment(
  @RequestBody final UserAssessment userAssessment) {
    UserAssessment addedUserAssessment = userAssessmentService
    .addUserAssessment(userAssessment);
    return ResponseEntity.ok(addedUserAssessment);
  }
  /**
   * Get the List of UserAssessments of a specific User ID.
   * @param userId from User Entity to return their assessments.
   * @return the List of the UserAssessments associated to a Specific UserId.
   */
  @GetMapping("/user/{userId}")
  public final ResponseEntity<List<UserAssessment>> getUserAssessmentsByUserId(
  @PathVariable final Long userId) {
    List<UserAssessment> userAssessment = userAssessmentService
    .getUserAssessmentsByUser(userId);
    return ResponseEntity.ok(userAssessment);
  }
  /**
   * Get the List of User Assessments of a specific questionID.
   * @param questionId from Question Entity to return the assessments.
   * @return the List of the UserAssessments associated to a QuestionId.
   */
  @GetMapping("/question/{questionId}")
  public final ResponseEntity<List<UserAssessment>>
  getUserAssessmentsByQuestion(@PathVariable final Long questionId) {
    Question question = questionService.getQuestionById(questionId);
    List<UserAssessment> userAssessments = userAssessmentService
    .getUserAssessmentByQuestion(question);
    return ResponseEntity.ok(userAssessments);
  }
  /**
   * Get the List of User Assessments by the userAssessmentID.
   * @param userAssessmentId as a path variable to return the assessments.
   * @return the UserAssessments of that Id.
   */
  @GetMapping("/{userAssessmentId}")
  public final ResponseEntity<UserAssessment> getUserAssessmentById(
  @PathVariable final Long userAssessmentId) {
    UserAssessment updatedUserAssessment = userAssessmentService
    .getUserAssessmentById(userAssessmentId);
    if (updatedUserAssessment != null) {
      return ResponseEntity.ok(updatedUserAssessment);
    } else {
      return ResponseEntity.notFound().build();
    }
  }
  /**
   * This deletes the userAssessment by an Id provided.
   * @param userAssessmentById of Long Type as input.
   * @return the success code that the userAssessment is deleted.
   */
  @DeleteMapping("/{userAssessmentById}")
  public final ResponseEntity<Void> deleteUserAssessmentById(
  @PathVariable final Long userAssessmentById) {
    userAssessmentService.deleteAssessmentById(userAssessmentById);
    return ResponseEntity.noContent().build();
  }
  /**
   * Method to update the UserAssessmentId.
   * @param userAssessmentId of Long Type to update.
   * @param updatedAssessment of UserAssessment to update the UserAssessment.
   * @return the updated UserAssessment.
   */
  @PutMapping("/{userAssessmentId}")
  public final ResponseEntity<UserAssessment> updateAssessment(
  @PathVariable final Long userAssessmentId,
  @RequestBody final UserAssessment updatedAssessment) {
    UserAssessment updatedUserAssessment = userAssessmentService
    .updateAssessment(userAssessmentId,  updatedAssessment);
    return ResponseEntity.ok(updatedUserAssessment);
  }
}
