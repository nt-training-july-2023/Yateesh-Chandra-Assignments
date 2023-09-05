package com.capstoneproject.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.capstoneproject.models.Category;
import com.capstoneproject.service.CategoryService;

class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;
    
    @InjectMocks
    private CategoryController categoryController;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testGetAllCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category());
        categories.add(new Category());
        when(categoryService.getAllCategories()).thenReturn(categories);
        ResponseEntity<List<Category>> response = categoryController.getAllCategories();
        
        verify(categoryService, times(1)).getAllCategories();
        assert response.getStatusCode()==HttpStatus.OK;
        assert response.getBody() != null && response.getBody().size()==2;
    }
    
    @Test
    public void testAddCategory() {
        Category category = new Category();
        when(categoryService.addCategory(category)).thenReturn(category);
        
        ResponseEntity<Category> response = categoryController.addCategory(category);
        verify(categoryService, times(1)).addCategory(category);
        assert response.getStatusCode()==HttpStatus.OK;
        assert response.getBody()!= null;
        
    }

    @Test
    public void testGetCategoryById() {
        Long categoryId = 4L;
        Category category = new Category();
        when(categoryService.getCategoryById(categoryId)).thenReturn(category);
        
        ResponseEntity<Category> response = categoryController.getCategoryById(categoryId);
        verify(categoryService, times(1)).getCategoryById(categoryId);
        assert response.getStatusCode()==HttpStatus.OK;
        assert response.getBody()!=null;
    }
    
    @Test
    public void testDeleteCategory() {
        Long categoryId = 5L;
        doNothing().when(categoryService).deleteCategory(categoryId);
        ResponseEntity<Void> response = categoryController.deleteCategory(categoryId);
        verify(categoryService, times(1)).deleteCategory(categoryId);
        assert response.getStatusCode()==HttpStatus.NO_CONTENT;
    }
    
    @Test
    public void testUpdateCategory() {
        Long categoryId = 4L;
        Category updatedCategory = new Category();
        when(categoryService.updateCategory(categoryId, updatedCategory)).thenReturn(updatedCategory);
        ResponseEntity<Category> response = categoryController.updateCategory(categoryId, updatedCategory);
        verify(categoryService, times(1)).updateCategory(categoryId, updatedCategory);
        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody()!=null;
    }
}
