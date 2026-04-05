package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Medicine;
import com.example.demo.repository.MedicineRepository;

import java.util.List;

@Service
public class MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    // GET ALL MEDICINES
    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }

    // ADD MEDICINE (optional for admin)
    public Medicine saveMedicine(Medicine medicine) {
        return medicineRepository.save(medicine);
    }
}