/**
 * This package contains the entity class for quiz.
 */
package com.capstoneproject.models;

import java.util.ArrayList;
import java.util.List;

import com.capstoneproject.response.ValidationMessages;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class is an entity class for Quiz.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "quiz")
public class Quiz {
    /**
     * This is the quiz ID column, Primary key.
     */
    @Id
    @Column(name = "quiz_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizId;

    /**
     * This is the Quiz name Column.
     */
    @Column(name = "quiz_name",unique = true, nullable = false)
    @NotEmpty(message = ValidationMessages.QUIZ_NAME_NOTBLANK)
    private String quizName;

    /**
     * This is the Quiz Description Column.
     */
    @Column(name = "quiz_description", nullable = false)
    @NotEmpty(message = ValidationMessages.QUIZ_DESC_NOTBLANK)
    private String quizDescription;

    /**
     * This column contains the number of questions of integer type.
     */
    @Column(name = "num_of_questions", nullable = false)
    @Basic(fetch = FetchType.EAGER)
    @NotNull(message = ValidationMessages.QUESTIONS_NOTNULL)
    private int numOfQuestions;

    /**
     * This column contains the timer in minutes.
     */
    @Column(nullable = false)
    @NotNull(message = ValidationMessages.TIME_NOTNULL)
    private int timeInMin;

    /**
     * This is Category id column from Category Entity.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    /**
     * This is Questions List mapped by Quiz.
     */
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Question> questions = new ArrayList<>();

    /**
     * This joins the column from User Responses.
     */
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UserResponses> userResponses = new ArrayList<>();
    /**
     * List the questions.
     *@return the list of questions.
     */
    public List<Question> getQuestions() {
        return new ArrayList<>(questions);
    }

    /**
     * This is Setter method for the Question Table.
     * @param que contains the list of questions.
     */
    public void setQuestions(final List<Question> que) {
        this.questions = new ArrayList<>(que);
    }

    /**
     * Get category.
     * @return category that was set.
     */
    public Category getCategory() {
        return new Category(category.getCategoryId(),
                category.getCategoryName(), category.getDescription());
    }

    /**
     * Sets the category.
     * @param cate is final.
     */
    public void setCategory(final Category cate) {
            this.category = new Category(cate.getCategoryId(),
                    cate.getCategoryName(), cate.getDescription());
    }

    /**
     * This is the getter method for the User Response.
     * @return the List of Responses.
     */
    public List<UserResponses> getUserResponses() {
        return new ArrayList<>(userResponses);
    }

    /**
     * This is the setter method for User Responses.
     * @param userResponse - User Response.
     */
    public void setUserResponses(
            final List<UserResponses> userResponse) {
        this.userResponses = new ArrayList<>(userResponse);
    }

    /**
     * Quiz constructor.
     * @param id       id.
     * @param quizname Name of the quiz.
     * @param quizDesc the description.
     * @param numOfQue number of questions.
     * @param timeInmin timer in minutes.
     */
    public Quiz(final Long id, final String quizname, final String quizDesc,
            final int numOfQue, final int timeInmin) {
        this.quizId = id;
        this.quizName = quizname;
        this.quizDescription = quizDesc;
        this.numOfQuestions = numOfQue;
        this.timeInMin = timeInmin;
    }

}
