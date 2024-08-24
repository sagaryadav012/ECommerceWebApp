package com.app.ECommerceWebApp.dtos.cartItemDtos;

import lombok.Data;

@Data
public class RemoveCartItemDTO {
    private long cart_id;
    private long product_id;
}
