package com.app.ECommerceWebApp.services;

import com.app.ECommerceWebApp.models.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    public Category createCategory(String name);
//    public Category getCategoryByName(String name) throws CategoryNotFoundException;
    public Category getCategoryByName(String name);
    public List<Category> getAllCategories();
}
