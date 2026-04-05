package com.example.demo.dto;

public class PurchaseRequest {

    private Long userId;
    private Long medicineId;  // ✅ FIXED
    private int quantity;

    public PurchaseRequest() {
    }

    public PurchaseRequest(Long userId, Long medicineId, int quantity) {
        this.userId = userId;
        this.medicineId = medicineId;
        this.quantity = quantity;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public int getQuantity() {
        return quantity;
    }

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