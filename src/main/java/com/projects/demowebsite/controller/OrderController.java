package com.projects.demowebsite.controller;

import com.projects.demowebsite.model.CartItem;
import com.projects.demowebsite.model.Order;
import com.projects.demowebsite.model.OrderItem;
import com.projects.demowebsite.service.CartService;
import com.projects.demowebsite.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/order/place")
    public String placeOrder() {
        List<CartItem> cartItems = cartService.getCartItems();
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            orderItems.add(new OrderItem(cartItem.getProduct().getId(), cartItem.getQuantity()));
        }

        Order order = new Order(orderItems);
        orderService.placeOrder(order);
        cartService.clearCart(); // Clear the cart after placing the order
        return "redirect:/orders"; // Redirect to orders page after placing the order
    }

    @GetMapping("/orders")
    public String showOrders() {
        return "orders";
    }
}
