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

import com.capstoneproject.dto.CategoryDTO;
import com.capstoneproject.service.CategoryService;

import jakarta.validation.Valid;

/**
 * This class contains the controller.
 */
@RestController
@RequestMapping(path = "category")
@CrossOrigin(origins = "*")
public class CategoryController {
    /**
     * The categoryService variable is used to operate on Category Service.
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * Creating instance for the Logger.
     */
    private Logger logger = LoggerFactory.getLogger(AllResultsController.class);

    /**
     * Gets all the categories.
     * @return the list of categories.
     */
    @GetMapping
    public final ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categories = categoryService.getCategories();
        logger.info("Fetched Categories successfully");
        return ResponseEntity.ok(categories);
    }

    /**
     * Get the category of requested Id.
     * @param categoryId - a path variable whose category is required.
     * @return the category if exists.
     */
    @GetMapping("/{categoryId}")
    public final ResponseEntity<CategoryDTO> getCategoryById(
            @PathVariable final Long categoryId) {
        CategoryDTO newCategoryDto = categoryService
                .getCategoryById(categoryId);
        logger.info("Fetched Category by Id");
        return ResponseEntity.ok(newCategoryDto);
    }

    /**
     * Add the category.
     * @param categoryDto of Category type is requested to add into it.
     * @return the success status when added.
     */
    @PostMapping
    public final ResponseEntity<String> addCategory(
            @RequestBody @Valid final CategoryDTO categoryDto) {
        categoryService.addCategory(categoryDto);
        logger.info("Succesfully Added Category");
        return ResponseEntity.status(HttpStatus.CREATED).body(
                "Added Category Successfully");
    }

    /**
     * Deletes the Category.
     * @param categoryId to be specified that is not needed.
     * @return deleted successfully if not found.
     */
    @DeleteMapping("/{categoryId}")
    public final ResponseEntity<String> deleteCategory(
            @PathVariable final Long categoryId) {
        categoryService.deleteCategory(categoryId);
        String message =  "Category Deleted";
        logger.info(message);
        return ResponseEntity.noContent().header("Message", message).build();
    }

    /**
     * Updates the category .
     * @param categoryId of Long Type.
     * @param updatedCategoryDto the updated content.
     * @return the updated category status.
     */
    @PutMapping("/{categoryId}")
    public final ResponseEntity<String> updateCategory(
            @PathVariable final Long categoryId,
            @RequestBody @Valid final CategoryDTO updatedCategoryDto) {
        categoryService.updateCategory(categoryId, updatedCategoryDto);
        logger.info("Catgory Updated Successfully");
        return ResponseEntity.ok("Updated Category successfully");
    }
}
