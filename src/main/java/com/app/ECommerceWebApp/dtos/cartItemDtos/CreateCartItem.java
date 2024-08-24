package com.app.ECommerceWebApp.dtos.cartItemDtos;

import com.app.ECommerceWebApp.dtos.productDtos.CreateProductDTO;
import lombok.Data;

@Data
public class CreateCartItem {
    private CreateProductDTO productDTO;
    private int quantity;
}
