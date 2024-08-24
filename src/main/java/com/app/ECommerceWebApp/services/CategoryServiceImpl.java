package com.app.ECommerceWebApp.services;

import com.app.ECommerceWebApp.models.Category;
import com.app.ECommerceWebApp.repositories.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{
    private CategoryRepo categoryRepo;

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Category createCategory(String name) {
        Category category = new Category();
        category.setName(name);

        return categoryRepo.save(category);
    }

    @Override
    public Category getCategoryByName(String name) {
        Optional<Category> category = categoryRepo.findCategoryByName(name);
//        if(category.isEmpty())
        return category.get();
    }

    //    @Override
//    public Category getCategoryByName(String name) throws CategoryNotFoundException{
//        return categoryRepo.findCategoryByName(name).orElseThrow(() -> new CategoryNotFoundException("Category Not Found!"));
//    }


    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }
}
