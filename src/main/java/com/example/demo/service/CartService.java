package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CartItem;
import com.example.demo.entity.Medicine;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.MedicineRepository;

import java.util.*;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    // ✅ ADD TO CART
    public CartItem addToCart(CartItem item) {
        return cartRepository.save(item);
    }

    // ✅ GET CART WITH MEDICINE DETAILS
    public List<Map<String, Object>> getCartByUser(Long userId) {

        // 🔥 Safety check
        if (userId == null) {
            return new ArrayList<>();
        }

        List<CartItem> cartItems = cartRepository.findByUserId(userId);

        // 🔥 Prevent null
        if (cartItems == null || cartItems.isEmpty()) {
            return new ArrayList<>();
        }

        List<Map<String, Object>> result = new ArrayList<>();

        for (CartItem item : cartItems) {

            // 🔥 Safe fetch
            Optional<Medicine> optionalMedicine = medicineRepository.findById(item.getMedicineId());

            if (optionalMedicine.isPresent()) {

                Medicine medicine = optionalMedicine.get();

                Map<String, Object> map = new HashMap<>();
                map.put("medicineId", medicine.getId());
                map.put("name", medicine.getName());
                map.put("price", medicine.getPrice());
                map.put("quantity", item.getQuantity());

                result.add(map);
            }
        }

        return result;
    }

    // ✅ CLEAR CART AFTER PURCHASE
    public void clearCart(Long userId) {

        if (userId == null) {
            return;
        }

        List<CartItem> items = cartRepository.findByUserId(userId);

        if (items != null && !items.isEmpty()) {
            cartRepository.deleteAll(items);
        }
    }
}