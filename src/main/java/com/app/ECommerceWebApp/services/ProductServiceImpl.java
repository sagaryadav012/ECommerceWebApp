package com.app.ECommerceWebApp.services;

import com.app.ECommerceWebApp.controllers.CategoryController;
import com.app.ECommerceWebApp.dtos.categoryDtos.CreateCategoryDTO;
import com.app.ECommerceWebApp.exceptions.productExceptions.ProductNotFoundException;
import com.app.ECommerceWebApp.models.Category;
import com.app.ECommerceWebApp.models.Product;
import com.app.ECommerceWebApp.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepo productRepo;
    private CategoryController categoryController;

    @Autowired
    public ProductServiceImpl(ProductRepo productRepo, CategoryController categoryController) {
        this.productRepo = productRepo;
        this.categoryController = categoryController;
    }

    @Override
    public Product createProduct(String title, double price, String description, int availableQuantity, String categoryName){
        Category category = null;
        try{
            ResponseEntity<Category> categoryResponseEntity = categoryController.getCategory(categoryName);
            category = categoryResponseEntity.getBody();
        }
        catch (Exception exception){
            CreateCategoryDTO categoryDTO = new CreateCategoryDTO();
            categoryDTO.setName(categoryName);
            ResponseEntity<Category> controllerCategory = categoryController.createCategory(categoryDTO);
            category = controllerCategory.getBody();
        }

        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        product.setDescription(description);
        product.setAvailableQuantity(availableQuantity);
        product.setCategory(category);

        return productRepo.save(product);
    }

    @Override
    public Product updatePrice(long id, double price) throws ProductNotFoundException {
        Product product = this.getProductById(id);
        product.setPrice(price);
        return productRepo.save(product);
    }

    @Override
    public Product updateAvailableQuantity(long id, int quantity) throws ProductNotFoundException {
        Product product = this.getProductById(id);
        product.setAvailableQuantity(quantity);
        return productRepo.save(product);
    }

    @Override
    public Product getProductById(long id) throws ProductNotFoundException {
        return productRepo.findById(id).orElseThrow(() -> new ProductNotFoundException("Product Not Found!"));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public void deleteProduct(long id) throws ProductNotFoundException {
        Product product = this.productRepo.findById(id).orElseThrow(() -> new ProductNotFoundException("Product Not Found!"));
        productRepo.delete(product);
    }
}
