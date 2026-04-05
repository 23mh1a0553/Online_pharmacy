package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.service.MedicineService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @GetMapping("/medicines")
    public Object getAllMedicines() {
        return medicineService.getAllMedicines();
    }
}