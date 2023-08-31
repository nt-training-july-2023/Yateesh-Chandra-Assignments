package com.capstoneproject.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capstoneproject.models.Category;
import com.capstoneproject.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;

/**
 * This class contains the Service for Category.
 */
@Service
public class CategoryService {
    /**
     * This categoryRepository variable performs some operations.
     */
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * gets all categories.
     *
     * @return the List of all categories.
     */
    public final List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    /**
     * adds the category.
     *
     * @param category of type Category.
     * @return the Status of the category added.
     */
    public final Category addCategory(final Category category) {
        return categoryRepository.save(category);
    }

    /**
     * deletes the Category by Id.
     *
     * @param categoryId of Long Type.
     */
    public final void deleteCategory(final Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    /**
     * updates the category.
     *
     * @param categoryId      of Long Type.
     * @param updatedCategory of Category Type.
     * @return the status of the updated category.
     */
    public final Category updateCategory(final Long categoryId,
            final Category updatedCategory) {
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Category Not Found"));
        existingCategory.setCategoryName(updatedCategory.getCategoryName());
        existingCategory.setDescription(updatedCategory.getDescription());
        return categoryRepository.save(existingCategory);
    }

    /**
     * gets the category by category Id.
     *
     * @param categoryId of Long type.
     * @return the CategoryId if exist.
     */
    public final Category getCategoryById(final Long categoryId) {
        try {
            Category existingCategory = categoryRepository.findById(categoryId)
                    .get();
            return existingCategory;
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("Element is not found");
        }
    }
}
