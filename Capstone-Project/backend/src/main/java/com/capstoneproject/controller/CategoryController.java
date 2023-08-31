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
import com.capstoneproject.service.CategoryService;

/**
 * This class contains the controller.
 */
@RestController
@RequestMapping(path = "api/v1/category")
@CrossOrigin
public class CategoryController {
    /**
     * The categoryService variable is used to operate on Category Service.
     */
    @Autowired
    private CategoryService categoryService;
    /**
     * Gets all the categories.
     * @return the list of categories.
     */
    @GetMapping
    public final ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }
    /**
     * Get the category of requested Id.
     * @param categoryId - a path variable whose category is required.
     * @return the category if exists.
     */
    @GetMapping("/{categoryId}")
    public final ResponseEntity<Category> getCategoryById(
            @PathVariable final Long categoryId) {
        Category newCategory = categoryService.getCategoryById(categoryId);
        return ResponseEntity.ok(newCategory);
    }
    /**
     * Add the category.
     * @param category of Category type is requested to add into it.
     * @return the success status when added.
     */
    @PostMapping
    public final ResponseEntity<Category> addCategory(
            @RequestBody final Category category) {
        Category newCategory = categoryService.addCategory(category);
        return ResponseEntity.ok(newCategory);
    }
    /**
     * Deletes the Category.
     * @param categoryId to be specified that is not needed.
     * @return deleted successfully if not found.
     */
    @DeleteMapping("/{categoryId}")
    public final ResponseEntity<Void> deleteCategory(
            @PathVariable final Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }
    /**
     * Updates the category .
     * @param categoryId of Long Type.
     * @param updatedCategory the updated content.
     * @return the updated category status.
     */
    @PutMapping("/{categoryId}")
    public final ResponseEntity<Category> updateCategory(
            @PathVariable final Long categoryId,
            @RequestBody final  Category updatedCategory) {
        Category newCategory = categoryService.updateCategory(categoryId,
                updatedCategory);
        return ResponseEntity.ok(newCategory);
    }
}
