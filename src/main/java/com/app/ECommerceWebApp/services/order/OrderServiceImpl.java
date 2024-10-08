package com.app.ECommerceWebApp.services.order;

import com.app.ECommerceWebApp.controllers.CartController;
import com.app.ECommerceWebApp.controllers.UserController;
import com.app.ECommerceWebApp.exceptions.orderExceptions.OrderNotFoundException;
import com.app.ECommerceWebApp.models.*;
import com.app.ECommerceWebApp.repositories.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    private OrderRepo orderRepo;
    private UserController userController;

    @Autowired
    public OrderServiceImpl(OrderRepo orderRepo, UserController userController) {
        this.orderRepo = orderRepo;
        this.userController = userController;
    }

    @Override
    public Order createOrder(long user_id) {
        /*
            1. Get user using user id.
            2. Get cart of user.
            3. Get list of cart items using cart.
         */
        User user = this.userController.getUSer(user_id).getBody();

        assert user != null;
        Cart cart = user.getCart();

        assert cart != null;
        double totalAmount = cart.getTotalAmount();

        Order order = new Order();
        order.setOrderDate(new Date());
        order.setOrderStatus(OrderStatus.PLACED);
        order.setUserId(user);
        order.setTotalAmount(totalAmount);

        List<CartItem> cartItems = cart.getCartItems();
        List<OrderedProduct> orderedProductList = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            OrderedProduct orderedProduct = new OrderedProduct();
            orderedProduct.setProduct(cartItem.getProduct());
            orderedProduct.setQuantity(cartItem.getQuantity());
            orderedProduct.setOrder(order);
            orderedProductList.add(orderedProduct);
        }

        order.setOrderedProductList(orderedProductList);


        return this.orderRepo.save(order);
    }

    @Override
    public Order fetchOrder(long order_id) throws OrderNotFoundException {
        return this.orderRepo.findById(order_id).orElseThrow(() -> new OrderNotFoundException("No Order Found!"));
    }

    @Override
    public List<Order> fetchOrders() {
        return this.orderRepo.findAll();
    }
}
