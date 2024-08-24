package com.app.ECommerceWebApp.controllers;

import com.app.ECommerceWebApp.dtos.categoryDtos.CreateCategoryDTO;
import com.app.ECommerceWebApp.models.Category;
import com.app.ECommerceWebApp.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Category")
public class CategoryController {
    private CategoryService categoryService;

    @PostMapping("/insert")
    public ResponseEntity<Category> createCategory(@RequestBody CreateCategoryDTO categoryDTO){
        Category category = categoryService.createCategory(categoryDTO.getName());
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @GetMapping("/fetch/{name}")
    public ResponseEntity<Category> getCategory(@PathVariable String name){
        Category category = this.categoryService.getCategoryByName(name);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<List<Category>> getCategory(){
        List<Category> allCategories = this.categoryService.getAllCategories();
        return new ResponseEntity<>(allCategories, HttpStatus.OK);
    }
}
