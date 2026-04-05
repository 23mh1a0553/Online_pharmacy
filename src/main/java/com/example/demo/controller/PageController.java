package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    // 🔐 Login Page
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // 📝 Register Page
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    // 🏠 Home Page
    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    // 👤 User Dashboard
    @GetMapping("/dashboard")
    public String userDashboard() {
        return "dashboard";
    }

    // 👨‍💼 Admin Dashboard
    @GetMapping("/admin-dashboard")
    public String adminDashboard() {
        return "admin-dashboard";
    }

    // 🛒 Cart Page
    @GetMapping("/cart")
    public String cartPage() {
        return "cart";
    }

    // 📦 Orders Page
    @GetMapping("/orders")
    public String ordersPage() {
        return "orders";
    }

    // 💊 Medicines Page
    @GetMapping("/medicines")
    public String medicinesPage() {
        return "medicines";
    }
}