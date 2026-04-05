package com.example.demo.dto;

import java.time.LocalDateTime;

public class PurchaseResponse {

    private String message;
    private Long orderId;           // ✅ FIXED
    private String medicineName;    // ✅ FIXED
    private int quantity;
    private double totalAmount;
    private LocalDateTime timestamp;

    public PurchaseResponse() {
    }

    public PurchaseResponse(String message, Long orderId, String medicineName,
                            int quantity, double totalAmount, LocalDateTime timestamp) {
        this.message = message;
        this.orderId = orderId;
        this.medicineName = medicineName;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}