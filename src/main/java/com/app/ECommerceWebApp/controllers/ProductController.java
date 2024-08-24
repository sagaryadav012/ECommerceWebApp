package com.app.ECommerceWebApp.controllers;

import com.app.ECommerceWebApp.dtos.productDtos.CreateProductDTO;
import com.app.ECommerceWebApp.dtos.productDtos.UpdateProductPriceDTO;
import com.app.ECommerceWebApp.dtos.productDtos.UpdateProductQuantityDTO;
import com.app.ECommerceWebApp.exceptions.productExceptions.ProductNotFoundException;
import com.app.ECommerceWebApp.models.Product;
import com.app.ECommerceWebApp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/insert")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductDTO productDTO){
        Product product = this.productService.createProduct(productDTO.getTitle(), productDTO.getPrice(), productDTO.getDescription(),
                productDTO.getAvailableQuantity(), productDTO.getCategoryDto().getName());
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping("/fetch/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable long id){
        try {
            Product product = productService.getProductById(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> allProducts = productService.getAllProducts();
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }

    @PostMapping("/updatePrice")
    public ResponseEntity<Product> updatePrice(@RequestBody UpdateProductPriceDTO productPriceDTO){
        // validate DTO

        try {
            Product product = productService.updatePrice(productPriceDTO.getId(), productPriceDTO.getPrice());
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (ProductNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/updateQuantity")
    public ResponseEntity<Product> updateQuantity(@RequestBody UpdateProductQuantityDTO productQuantityDTO){
        // validate DTO

        try {
            Product product = productService.updateAvailableQuantity(productQuantityDTO.getId(), productQuantityDTO.getQuantity());
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (ProductNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id){
        try {
            this.productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ProductNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


//    public void validateProductDTO(CreateProductDTO productDTO){
//
//    }
}
