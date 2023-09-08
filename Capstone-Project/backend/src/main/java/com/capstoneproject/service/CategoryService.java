package com.capstoneproject.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstoneproject.dto.CategoryDTO;
import com.capstoneproject.exceptions.AlreadyExistsException;
import com.capstoneproject.exceptions.ElementNotExistsException;
import com.capstoneproject.exceptions.NoInputException;
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
    public final List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
        .map(this::convertModelToDto)
        .collect(Collectors.toList());
    }

    private CategoryDTO convertModelToDto(final Category category) {
        CategoryDTO categoryDto = new CategoryDTO();
        categoryDto.setCategoryId(category.getCategoryId());
        categoryDto.setCategoryName(category.getCategoryName());
        categoryDto.setDescription(category.getDescription());
        return categoryDto;
    }

    /**
     * adds the category.
     *
     * @param category of type Category.
     * @return the Status of the category added.
     */
    public final CategoryDTO addCategory(final CategoryDTO categoryDto) {
        if(categoryDto.getCategoryName().isEmpty()) {
            throw new NoInputException();
        }
        
        else {
            Optional<Category> category= categoryRepository.getCategoryByName(categoryDto.getCategoryName());
            if(category.isPresent()) {
                throw new AlreadyExistsException();
            }
            Category newCategory = new Category();
            newCategory.setCategoryId(categoryDto.getCategoryId());
            newCategory.setCategoryName(categoryDto.getCategoryName());
            newCategory.setDescription(categoryDto.getDescription());
            categoryRepository.save(newCategory);
            return categoryDto;
        }
    }

    /**
     * deletes the Category by Id.
     *
     * @param categoryId of Long Type.
     */
    public final void deleteCategory(final Long categoryId) {
        Category existingCategory = categoryRepository.findById(categoryId).orElse(null);
        if(existingCategory == null) {
            throw new ElementNotExistsException();
        }
        else {
        categoryRepository.deleteById(categoryId);
        }
    }

    /**
     * updates the category.
     *
     * @param categoryId of Long Type.
     * @param updatedCategory of Category Type.
     * @return the status of the updated category.
     */
    public final CategoryDTO updateCategory(final Long categoryId,
            final CategoryDTO updatedCategory) {
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElse(null);
        if(existingCategory != null) {
            existingCategory.setCategoryId(updatedCategory.getCategoryId());
            existingCategory.setCategoryName(updatedCategory.getCategoryName());
            existingCategory.setDescription(updatedCategory.getDescription());
            
            if(existingCategory.getCategoryName().isEmpty()) {
                throw new NoInputException();
            }
            Category newCategory = categoryRepository.save(existingCategory);
            CategoryDTO newCategoryDto = new CategoryDTO();
            newCategoryDto.setCategoryId(newCategory.getCategoryId());
            newCategoryDto.setCategoryName(newCategory.getCategoryName());
            newCategoryDto.setDescription(newCategory.getDescription());
            return newCategoryDto;
        }
        else {
            throw new ElementNotExistsException();
        }
    }

    /**
     * gets the category by category Id.
     *
     * @param categoryId of Long type.
     * @return the CategoryId if exist.
     */
    public final CategoryDTO getCategoryById(final Long categoryId) {
        Category existingCategory = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new EntityNotFoundException("Category not found with Id : " + categoryId));
        CategoryDTO categoryDto = new CategoryDTO();
        categoryDto.setCategoryId(existingCategory.getCategoryId());
        categoryDto.setCategoryName(existingCategory.getCategoryName());
        categoryDto.setDescription(existingCategory.getDescription());
        return categoryDto;
    }
}
