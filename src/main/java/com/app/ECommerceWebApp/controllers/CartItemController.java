package com.app.ECommerceWebApp.controllers;

import com.app.ECommerceWebApp.dtos.cartItemDtos.CreateCartItem;
import com.app.ECommerceWebApp.models.CartItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cartItem")
public class CartItemController {

//    @PostMapping("/addCartItem")
//    public ResponseEntity<CartItem> addCartItem(@RequestBody CreateCartItem cartItem){
//        return ne
//    }
}
