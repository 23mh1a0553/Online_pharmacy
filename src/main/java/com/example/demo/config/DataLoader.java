package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Medicine;
import com.example.demo.repository.MedicineRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public void run(String... args) {

        if (medicineRepository.count() > 0) return;

        medicineRepository.save(new Medicine("Crocin", 60.0, "https://cdn-icons-png.flaticon.com/512/2966/2966327.png"));
        medicineRepository.save(new Medicine("Paracetamol", 40.0, "https://cdn-icons-png.flaticon.com/512/4320/4320371.png"));
        medicineRepository.save(new Medicine("Dolo 650", 55.0, "https://cdn-icons-png.flaticon.com/512/3771/3771446.png"));
        medicineRepository.save(new Medicine("Aspirin", 30.0, "https://cdn-icons-png.flaticon.com/512/3785/3785856.png"));
        medicineRepository.save(new Medicine("Vitamin C", 80.0, "https://cdn-icons-png.flaticon.com/512/3075/3075977.png"));

        medicineRepository.save(new Medicine("Zinc Tablets", 90.0, "https://cdn-icons-png.flaticon.com/512/2833/2833927.png"));
        medicineRepository.save(new Medicine("Calcium Tablets", 120.0, "https://cdn-icons-png.flaticon.com/512/3771/3771445.png"));
        medicineRepository.save(new Medicine("Ibuprofen", 70.0, "https://cdn-icons-png.flaticon.com/512/4320/4320337.png"));
        medicineRepository.save(new Medicine("Amoxicillin", 110.0, "https://cdn-icons-png.flaticon.com/512/2966/2966330.png"));
        medicineRepository.save(new Medicine("Cough Syrup", 95.0, "https://cdn-icons-png.flaticon.com/512/3771/3771417.png"));

        medicineRepository.save(new Medicine("Insulin", 250.0, "https://cdn-icons-png.flaticon.com/512/2966/2966329.png"));
        medicineRepository.save(new Medicine("Antacid", 50.0, "https://cdn-icons-png.flaticon.com/512/4320/4320360.png"));
        medicineRepository.save(new Medicine("ORS Sachet", 25.0, "https://cdn-icons-png.flaticon.com/512/3771/3771452.png"));
        medicineRepository.save(new Medicine("Eye Drops", 85.0, "https://cdn-icons-png.flaticon.com/512/4320/4320355.png"));
        medicineRepository.save(new Medicine("Nasal Spray", 140.0, "https://cdn-icons-png.flaticon.com/512/4320/4320365.png"));

        medicineRepository.save(new Medicine("Pain Relief Gel", 150.0, "https://cdn-icons-png.flaticon.com/512/3771/3771424.png"));
        medicineRepository.save(new Medicine("Thermometer", 200.0, "https://cdn-icons-png.flaticon.com/512/2966/2966332.png"));
        medicineRepository.save(new Medicine("Hand Sanitizer", 60.0, "https://cdn-icons-png.flaticon.com/512/2913/2913461.png"));
        medicineRepository.save(new Medicine("Face Mask Pack", 100.0, "https://cdn-icons-png.flaticon.com/512/2966/2966334.png"));
        medicineRepository.save(new Medicine("Bandage Roll", 45.0, "https://cdn-icons-png.flaticon.com/512/3771/3771433.png"));

        System.out.println("✅ 20 Medicines Loaded Successfully!");
    }
}