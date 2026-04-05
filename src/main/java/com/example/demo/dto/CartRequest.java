package com.example.demo.dto;

public class CartRequest {

    private Long userId;
    private Long medicineId;
    private int quantity;

    // Getters
    public Long getUserId() {
        return userId;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public int getQuantity() {
        return quantity;
    }

    // Setters
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}