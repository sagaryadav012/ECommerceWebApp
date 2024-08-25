package com.app.ECommerceWebApp.controllers;

import com.app.ECommerceWebApp.dtos.cartItemDtos.AddCartItemDTO;
import com.app.ECommerceWebApp.dtos.cartItemDtos.RemoveCartItemDTO;
import com.app.ECommerceWebApp.dtos.cartItemDtos.UpdateQuantityDTO;
import com.app.ECommerceWebApp.exceptions.cartExceptions.CartNotExistsException;
import com.app.ECommerceWebApp.models.Cart;
import com.app.ECommerceWebApp.services.cart.CartService;
import com.app.ECommerceWebApp.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    private CartService cartService;
    private AuthUtils authUtils;

    @Autowired
    public CartController(CartService cartService, AuthUtils authUtils) {
        this.cartService = cartService;
        this.authUtils = authUtils;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> viewCart(@PathVariable long id, @RequestHeader String value){
        if(!authUtils.validate_token(value)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        try {
            Cart cart = this.cartService.viewCart(id);
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } catch (CartNotExistsException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/addCartItem")
    public ResponseEntity<Cart> addCartItem(@RequestBody AddCartItemDTO cartItemDTO, @RequestHeader String value){
        if(!authUtils.validate_token(value)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        try {
            Cart cart = this.cartService.addCartItem(cartItemDTO.getCart_id(), cartItemDTO.getProduct_id(), cartItemDTO.getQuantity());
            return new ResponseEntity<>(cart, HttpStatus.CREATED);
        } catch (CartNotExistsException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/removeCartItem")
    public ResponseEntity<Cart> removeCartItem(@RequestBody RemoveCartItemDTO removeCartItemDTO, @RequestHeader String value){
        if(!authUtils.validate_token(value)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        try {
            Cart cart = this.cartService.removeCartItem(removeCartItemDTO.getCart_id(), removeCartItemDTO.getProduct_id());
            return new ResponseEntity<>(cart, HttpStatus.NO_CONTENT);
        } catch (CartNotExistsException e) {
            throw new RuntimeException(e);
        }

    }

    @PatchMapping("/updateQuantity")
    public ResponseEntity<Cart> updateQuantity(@RequestBody UpdateQuantityDTO updateQuantityDTO, @RequestHeader String value){
        if(!authUtils.validate_token(value)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        try {
            Cart cart = this.cartService.updateQuantity(updateQuantityDTO.getCart_id(), updateQuantityDTO.getProduct_id(), updateQuantityDTO.getQuantity());
            return new ResponseEntity<>(cart, HttpStatus.CREATED);
        } catch (CartNotExistsException e) {
            throw new RuntimeException(e);
        }
    }
}
