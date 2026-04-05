package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

@Service
public class PdfService {

    public byte[] generateInvoice(Long userId, List<Map<String, Object>> items, double total) {

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("ONLINE PHARMACY INVOICE"));
            document.add(new Paragraph("User ID: " + userId));
            document.add(new Paragraph(" "));

            for (Map<String, Object> item : items) {
                document.add(new Paragraph(
                        item.get("name") + " - Qty: " +
                        item.get("quantity") + " - ₹" +
                        item.get("price")
                ));
            }

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Total Amount: ₹" + total));

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return out.toByteArray();
    }
}