package com.capstoneproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstoneproject.models.Category;
import com.capstoneproject.models.Quiz;
import com.capstoneproject.repository.QuizRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class QuizService {

	@Autowired
	private QuizRepository quizRepository;
	
	public List<Quiz> getAllQuiz(){
		return quizRepository.findAll();
	}
	
	public List<Quiz> getQuizByCategoryId(Category category){
		return quizRepository.findByCategory(category);
	}
	
	public Quiz getQuizById(Long quizId) {
		Quiz existingQuiz = quizRepository.findById(quizId).get();
		return existingQuiz;
	}
	
	public Quiz addQuiz(Quiz quiz) {
		return quizRepository.save(quiz);
	}
	
	public void deleteQuiz(Long quizId) {
		quizRepository.deleteById(quizId);
	}
	
	public Quiz updateQuiz(Long quizId, Quiz updatedQuiz) {
		Quiz existingQuiz = quizRepository.findById(quizId).orElseThrow(()-> new EntityNotFoundException("Quiz Not Found.!"));
		existingQuiz.setQuizName(updatedQuiz.getQuizName());
		existingQuiz.setQuizDescription(updatedQuiz.getQuizDescription());
		existingQuiz.setNumOfQuestions(updatedQuiz.getNumOfQuestions());
		existingQuiz.setCategory(updatedQuiz.getCategory());
		return quizRepository.save(existingQuiz);
	}
}
