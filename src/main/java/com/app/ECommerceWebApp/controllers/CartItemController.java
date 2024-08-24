package com.app.ECommerceWebApp.controllers;

import com.app.ECommerceWebApp.dtos.cartItemDtos.AddCartItemDTO;
import com.app.ECommerceWebApp.dtos.cartItemDtos.RemoveCartItemDTO;
import com.app.ECommerceWebApp.dtos.cartItemDtos.UpdateQuantityDTO;
import com.app.ECommerceWebApp.exceptions.cartItemExceptions.ProductExistException;
import com.app.ECommerceWebApp.exceptions.cartItemExceptions.ProductNotExistException;
import com.app.ECommerceWebApp.models.CartItem;
import com.app.ECommerceWebApp.services.CartItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartItem")
public class CartItemController {
    private CartItemService cartItemService;

    @PostMapping("/addCartItem")
    public ResponseEntity<CartItem> addCartItem(@RequestBody AddCartItemDTO cartItemDTO){
        try {
            CartItem cartItem = this.cartItemService.addCartItem(cartItemDTO.getCart_id(), cartItemDTO.getProduct_id(), cartItemDTO.getQuantity());
            return new ResponseEntity<>(cartItem, HttpStatus.CREATED);
        } catch (ProductExistException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/removeCartItem")
    public ResponseEntity<CartItem> removeCartItem(@RequestBody RemoveCartItemDTO cartItemDTO){
        try {
            CartItem cartItem = this.cartItemService.removerCartItem(cartItemDTO.getCart_id(), cartItemDTO.getProduct_id());
            return new ResponseEntity<>(cartItem, HttpStatus.NO_CONTENT);
        } catch (ProductNotExistException e) {
            throw new RuntimeException(e);
        }
    }

    @PatchMapping("/updateQuantity")
    public ResponseEntity<CartItem> updateQuantity(@RequestBody UpdateQuantityDTO quantityDTO){
        try {
            CartItem cartItem = this.cartItemService.updateQuantity(quantityDTO.getCart_id(), quantityDTO.getProduct_id(), quantityDTO.getQuantity());
            return new ResponseEntity<>(cartItem, HttpStatus.CREATED);
        } catch (ProductNotExistException e) {
            throw new RuntimeException(e);
        }
    }
}
