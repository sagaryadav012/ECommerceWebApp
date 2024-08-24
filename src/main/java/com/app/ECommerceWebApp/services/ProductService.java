package com.app.ECommerceWebApp.services;

import com.app.ECommerceWebApp.exceptions.productExceptions.ProductNotFoundException;
import com.app.ECommerceWebApp.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    Product createProduct(String title, double price, String description, int availableQuantity, String categoryName);
    Product updatePrice(long id, double price) throws ProductNotFoundException;
    Product updateAvailableQuantity(long id, int quantity) throws ProductNotFoundException;
    Product getProductById(long id) throws ProductNotFoundException;
    List<Product> getAllProducts();
    void deleteProduct(long id) throws ProductNotFoundException;
}
