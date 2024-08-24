package com.app.ECommerceWebApp.controllers;

import com.app.ECommerceWebApp.dtos.cartItemDtos.AddCartItemDTO;
import com.app.ECommerceWebApp.dtos.cartItemDtos.RemoveCartItemDTO;
import com.app.ECommerceWebApp.dtos.cartItemDtos.UpdateQuantityDTO;
import com.app.ECommerceWebApp.exceptions.cartExceptions.CartNotExistsException;
import com.app.ECommerceWebApp.models.Cart;
import com.app.ECommerceWebApp.services.CartItemService;
import com.app.ECommerceWebApp.services.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    private CartService cartService;

    @GetMapping("/viewCart/{id}")
    public ResponseEntity<Cart> viewCart(@PathVariable long id){
        try {
            Cart cart = this.cartService.viewCart(id);
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } catch (CartNotExistsException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/addCartItem")
    public ResponseEntity<Cart> addCartItem(@RequestBody AddCartItemDTO cartItemDTO){
        try {
            Cart cart = this.cartService.addCartItem(cartItemDTO.getCart_id(), cartItemDTO.getProduct_id(), cartItemDTO.getQuantity());
            return new ResponseEntity<>(cart, HttpStatus.CREATED);
        } catch (CartNotExistsException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/removeCartItem")
    public ResponseEntity<Cart> removeCartItem(@RequestBody RemoveCartItemDTO removeCartItemDTO){
        try {
            Cart cart = this.cartService.removeCartItem(removeCartItemDTO.getCart_id(), removeCartItemDTO.getProduct_id());
            return new ResponseEntity<>(cart, HttpStatus.NO_CONTENT);
        } catch (CartNotExistsException e) {
            throw new RuntimeException(e);
        }

    }

    @PatchMapping("/updateQuantity")
    public ResponseEntity<Cart> updateQuantity(@RequestBody UpdateQuantityDTO updateQuantityDTO){
        Cart cart = this.cartService.updateQuantity(updateQuantityDTO.getCart_id(), updateQuantityDTO.getProduct_id(), updateQuantityDTO.getQuantity());
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }
}
