package com.app.ECommerceWebApp.controllers;

import com.app.ECommerceWebApp.dtos.categoryDtos.CreateCategoryDTO;
import com.app.ECommerceWebApp.exceptions.categoryExceptions.CategoryNotFoundException;
import com.app.ECommerceWebApp.models.Category;
import com.app.ECommerceWebApp.services.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/insert")
    public ResponseEntity<Category> createCategory(@RequestBody CreateCategoryDTO categoryDTO){
        Category category = categoryService.createCategory(categoryDTO.getName());
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @GetMapping("/fetch/{name}")
    public ResponseEntity<Category> getCategory(@PathVariable String name){
        try {
            Category category = this.categoryService.getCategoryByName(name);
            return new ResponseEntity<>(category, HttpStatus.OK);
        } catch (CategoryNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<List<Category>> getCategory(){
        List<Category> allCategories = this.categoryService.getAllCategories();
        return new ResponseEntity<>(allCategories, HttpStatus.OK);
    }
}
