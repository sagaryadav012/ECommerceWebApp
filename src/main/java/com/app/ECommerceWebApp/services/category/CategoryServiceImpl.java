package com.app.ECommerceWebApp.services.category;

import com.app.ECommerceWebApp.exceptions.categoryExceptions.CategoryNotFoundException;
import com.app.ECommerceWebApp.models.Category;
import com.app.ECommerceWebApp.repositories.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    private CategoryRepo categoryRepo;

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Category createCategory(String name) {
        /*
            1. Check category exists in db table first, If exists return it.
            2. Else create new category.
         */
        Category category = null;
        try {
            category = this.getCategoryByName(name);
        } catch (CategoryNotFoundException e) {
            category = new Category();
            category.setName(name);
            return categoryRepo.save(category);
        }
        return category;
    }

    @Override
    public Category getCategoryByName(String name) throws CategoryNotFoundException {
        return categoryRepo.findCategoryByName(name).orElseThrow(() -> new CategoryNotFoundException("Category Not Found!"));
    }


    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }
}
