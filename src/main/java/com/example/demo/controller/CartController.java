package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CartRequest;
import com.example.demo.entity.CartItem;
import com.example.demo.service.CartService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/cart/add")
    public Object addToCart(@RequestBody CartRequest req) {

        CartItem item = new CartItem();
        item.setUserId(req.getUserId());
        item.setMedicineId(req.getMedicineId());
        item.setQuantity(req.getQuantity());

        return cartService.addToCart(item);
    }

    @GetMapping("/cart/{userId}")
    public Object getCart(@PathVariable Long userId) {
        return cartService.getCartByUser(userId);
    }
}