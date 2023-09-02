package com.capstoneproject.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capstoneproject.models.Category;
import com.capstoneproject.repository.CategoryRepository;

import jakarta.persistence.EntityNotFoundException;

class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;
    
    @InjectMocks
    private CategoryService categoryService;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testGetAllCategories() {
        List<Category> expectedCategories = new ArrayList<>();
        when(categoryRepository.findAll()).thenReturn(expectedCategories);
        List<Category> actualCategories = categoryService.getAllCategories();
        assertEquals(expectedCategories, actualCategories);
    }
    
    @Test
    public void testaddCategory() {
        Category categoryToAdd = new Category();
        Category savedCategory = new Category();
        when(categoryRepository.save(categoryToAdd)).thenReturn(savedCategory);
        
        Category actualCategory = categoryService.addCategory(categoryToAdd);
        assertEquals(savedCategory, actualCategory);
    }
    
    @Test
    public void testDeleteCategory() {
        Long categoryIdToDelete = 1L;
        assertDoesNotThrow(() -> categoryService.deleteCategory(categoryIdToDelete));
        verify(categoryRepository, times(1)).deleteById(categoryIdToDelete);
    }
    
    @Test
    public void testUpdateCategory() {
        Long categoryId = 2L;
        Category updatedCategory = new Category();
        Category existingCategory = new Category();
        
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(existingCategory));
        when(categoryRepository.save(existingCategory)).thenReturn(updatedCategory);
        
        Category actualCategory = categoryService.updateCategory(categoryId, updatedCategory);
        assertEquals(updatedCategory, actualCategory);
    }
    
    @Test
    public void testUpdateCategoryEntityNotFound() {
        Long categoryIdToUpdate = 1L;
        Category updatedCategory = new Category();

        when(categoryRepository.findById(categoryIdToUpdate)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> categoryService.updateCategory(categoryIdToUpdate, updatedCategory));
    }

    @Test
    public void testGetCategoryById() {
        Long categoryId = 5L;
        Category expectedCategory = new Category();
        
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(expectedCategory));
        Category actualCategory = categoryService.getCategoryById(categoryId);
        assertEquals(expectedCategory, actualCategory);
    }
    
    @Test
    public void testGetCategoryByIdNotFound() {
        Long categoryId = 5L;
        
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());
        
        assertThrows(EntityNotFoundException.class, () -> categoryService.getCategoryById(categoryId));
    }
}
