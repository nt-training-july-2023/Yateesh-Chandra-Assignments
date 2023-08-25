package com.capstoneproject.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "quiz_table")
public class Quiz {

	@Id
	@Column(name = "quiz_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long quizId;
	
	@Column(name = "quiz_name")
	private String quizName;
	
	@Column(name = "quiz_description")
	private String quizDescription;
	
	@Column(name = "num_of_questions")
	private int numOfQuestions;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
}
