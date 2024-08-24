package com.app.ECommerceWebApp.dtos.productDtos;

import com.app.ECommerceWebApp.dtos.categoryDtos.CreateCategoryDTO;
import lombok.Data;

@Data
public class CreateProductDTO {
    private String title;
    private String description;
    private double price;
    private int availableQuantity;
    private CreateCategoryDTO categoryDto;
}
