package com.projects.demowebsite.service;

import com.projects.demowebsite.model.CartItem;
import com.projects.demowebsite.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private ProductService productService;

    private List<CartItem> cartItems = new ArrayList<>();

    public void addToCart(Long productId, int quantity) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            CartItem cartItem = new CartItem(product, quantity);
            cartItems.add(cartItem);
        }
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public double getTotal() {
        return cartItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

    public void clearCart() {
        cartItems.clear();
    }
}
