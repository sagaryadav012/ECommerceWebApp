package com.app.ECommerceWebApp.dtos.productDtos;

import lombok.Data;

@Data
public class UpdateProductQuantityDTO {
    private long id;
    private int quantity;
}
