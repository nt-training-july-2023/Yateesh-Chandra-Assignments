/**
 * The package is for the category model.
 */
package com.capstoneproject.models;

import java.util.ArrayList;
import java.util.List;

import com.capstoneproject.response.ValidationMessages;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This is Category Entity.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "category")
public class Category {
    /**
     * This is the Category ID column that is the primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;
    /**
     * This is the category name Column.
     */
    @Column(name = "category_name", nullable = false, unique = true)
    @NotBlank(message = ValidationMessages.CATEGORY_NAME_NOTBLANK)
    private String categoryName;
    /**
     * This is the Category Description Column.
     */
    @Column(name = "category_description", nullable = false)
    @NotBlank(message = ValidationMessages.CATEGORY_DESC_NOTBLANK)
    private String description;

    /**
     * This is the constructor with 3 parameters.
     * @param catId of Long type.
     * @param catName of String Type.
     * @param desc of String type.
     */
    public Category(final Long catId, final String catName,
            final String desc) {
        this.categoryId = catId;
        this.categoryName = catName;
        this.description = desc;
    }

    /**
     * This is the List of the Quiz mapped by Category.
     */
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Quiz> quiz = new ArrayList<>();
    /**
     * Quiz.
     * @return the list.
     */
    public List<Quiz> getQuiz() {
        return new ArrayList<>(quiz);
    }

    /**
     * Quiz Setter overriding.
     * @param q is quiz.
     */
    public void setQuiz(final List<Quiz> q) {
        this.quiz = new ArrayList<>(q);
    }

}
