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

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/quiz")
public class QuizController {

	@Autowired
	private QuizService quizService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<List<Quiz>> getAllQuiz(){
		List<Quiz> quiz = quizService.getAllQuiz();
		return ResponseEntity.ok(quiz);
	}
	
	@GetMapping("/{quizId}")
	public ResponseEntity<Quiz> getQuizById(@PathVariable Long quizId){
		Quiz newQuiz = quizService.getQuizById(quizId);
		return ResponseEntity.ok(newQuiz);
	}
	
	@PostMapping
	public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz){
		Quiz newQuiz = quizService.addQuiz(quiz);
		return ResponseEntity.ok(newQuiz);
	}
	
	@DeleteMapping("/{quizId}")
	public ResponseEntity<Void> deleteQuiz(@PathVariable Long quizId){
		quizService.deleteQuiz(quizId);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{quizId}")
	public ResponseEntity<Quiz> updateQuiz(@PathVariable Long quizId, @RequestBody Quiz updatedQuiz){
		Quiz newQuiz = quizService.updateQuiz(quizId, updatedQuiz);
		return ResponseEntity.ok(newQuiz);
 
	}
	
	@GetMapping("/byCategory/{categoryId}")
	public ResponseEntity<List<Quiz>> getQuizByCategoryId(@PathVariable Long categoryId){
		Category category = categoryService.getCategoryById(categoryId);
		if(category == null) {
			return ResponseEntity.notFound().build();
		}
		List<Quiz> newQuiz = quizService.getQuizByCategoryId(category);
		return ResponseEntity.ok(newQuiz);
	}
}
