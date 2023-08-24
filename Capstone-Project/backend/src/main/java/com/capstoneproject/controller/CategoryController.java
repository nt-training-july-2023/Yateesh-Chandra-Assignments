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

@RestController
@RequestMapping(path = "api/v1/category")
@CrossOrigin
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<List<Category>> getAllCategories(){
		List<Category> categories = categoryService.getAllCategories();
		return ResponseEntity.ok(categories);
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<Category> getCategoryById(@PathVariable Long categoryId){
		Category newCategory = categoryService.getCategoryById(categoryId);
		return ResponseEntity.ok(newCategory);
	}
	
	@PostMapping
	public ResponseEntity<Category> addCategory(@RequestBody Category category){
		Category newCategory = categoryService.addCategory(category);
		return ResponseEntity.ok(newCategory);
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId){
		categoryService.deleteCategory(categoryId);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<Category> updateCategory(@PathVariable Long categoryId, @RequestBody Category updatedCategory){
		Category newCategory = categoryService.updateCategory(categoryId, updatedCategory);
		return ResponseEntity.ok(newCategory);
	}
}
