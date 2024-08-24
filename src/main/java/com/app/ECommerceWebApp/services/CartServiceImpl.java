package com.app.ECommerceWebApp.services;

import com.app.ECommerceWebApp.controllers.CartItemController;
import com.app.ECommerceWebApp.dtos.cartItemDtos.AddCartItemDTO;
import com.app.ECommerceWebApp.dtos.cartItemDtos.RemoveCartItemDTO;
import com.app.ECommerceWebApp.dtos.cartItemDtos.UpdateQuantityDTO;
import com.app.ECommerceWebApp.exceptions.cartExceptions.CartNotExistsException;
import com.app.ECommerceWebApp.models.Cart;
import com.app.ECommerceWebApp.models.CartItem;
import com.app.ECommerceWebApp.repositories.CartRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CartServiceImpl implements CartService{
    private CartRepo cartRepo;
    private CartItemController cartItemController;

    @Override
    public Cart viewCart(long id) throws CartNotExistsException {
        return this.cartRepo.findById(id).orElseThrow(() -> new CartNotExistsException("Cart Is Empty!"));
    }

    @Override
    public Cart addCartItem(long cart_id, long product_id, int quantity) throws CartNotExistsException {
        /*
            1. Create DTO, set data and send it to CartItem, CartItem stores product in cart.
            2. Now update amount in cart, get product price and quantity from CartItem and update amount in cart.
         */
        AddCartItemDTO addCartItemDTO = this.getAddCartItemDTO(cart_id, product_id, quantity);
        ResponseEntity<CartItem> cartItemResponseEntity = this.cartItemController.addCartItem(addCartItemDTO);
        CartItem cartItem = cartItemResponseEntity.getBody();

        assert cartItem != null;
        double price = cartItem.getProduct().getPrice();
        double amount = price * quantity;

        return this.updateAmount(cart_id, amount);
    }

    @Override
    public Cart removeCartItem(long cart_id, long product_id) throws CartNotExistsException {
        RemoveCartItemDTO removeCartItemDTO = this.getRemoveCartItemDTO(cart_id, product_id);
        ResponseEntity<CartItem> cartItemResponseEntity = this.cartItemController.removeCartItem(removeCartItemDTO);
        CartItem cartItem = cartItemResponseEntity.getBody();
        assert cartItem != null;
        double price = cartItem.getProduct().getPrice();
        int quantity = cartItem.getQuantity();
        double amount = price * quantity;

        return this.updateAmount(cart_id, -amount); // - indicates subtract amount
    }

    @Override
    public Cart updateQuantity(long cart_id, long product_id, int quantity) {
        UpdateQuantityDTO updateQuantityDTO = this.getUpdateQuantityDTO(cart_id, product_id, quantity);
        ResponseEntity<CartItem> cartItemResponseEntity = this.cartItemController.updateQuantity(updateQuantityDTO);

        return Objects.requireNonNull(cartItemResponseEntity.getBody()).getCart();
    }

    public Cart updateAmount(long cart_id, double amount) throws CartNotExistsException {
        Cart cart = this.viewCart(cart_id);
        double totalAmount = cart.getTotalAmount();
        totalAmount += amount;
        cart.setTotalAmount(totalAmount);
        return this.cartRepo.save(cart);
    }

    public AddCartItemDTO getAddCartItemDTO(long cart_id, long product_id, int quantity){
        AddCartItemDTO addCartItemDTO = new AddCartItemDTO();
        addCartItemDTO.setCart_id(cart_id);
        addCartItemDTO.setProduct_id(product_id);
        addCartItemDTO.setQuantity(quantity);

        return addCartItemDTO;
    }

    public RemoveCartItemDTO getRemoveCartItemDTO(long cart_id, long product_id){
        RemoveCartItemDTO removeCartItemDTO = new RemoveCartItemDTO();
        removeCartItemDTO.setCart_id(cart_id);
        removeCartItemDTO.setProduct_id(product_id);

        return removeCartItemDTO;
    }

    public UpdateQuantityDTO getUpdateQuantityDTO(long cart_id, long product_id, int quantity){
        UpdateQuantityDTO updateQuantityDTO = new UpdateQuantityDTO();
        updateQuantityDTO.setCart_id(cart_id);
        updateQuantityDTO.setProduct_id(product_id);
        updateQuantityDTO.setQuantity(quantity);

        return updateQuantityDTO;
    }

}
