package com.app.ECommerceWebApp.dtos.cartItemDtos;

import lombok.Data;

@Data
public class AddCartItemDTO {
    private long cart_id;
    private long product_id;
    private int quantity;
}
