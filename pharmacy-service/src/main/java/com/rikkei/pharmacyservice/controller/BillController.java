package com.rikkei.pharmacyservice.controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;
@RestController @RequestMapping("/api/v1/bill") @RefreshScope public class BillController {
    @Value("${pharmacy.vat-rate:0.08}") private BigDecimal vatRate;
    @PostMapping public BillResponse calculate(@RequestBody BillRequest request) {
        BigDecimal tax = request.medicineTotal().multiply(vatRate);
        return new BillResponse(request.medicineTotal(), vatRate, tax, request.medicineTotal().add(tax));
    }
    public record BillRequest(BigDecimal medicineTotal) {}
    public record BillResponse(BigDecimal medicineTotal, BigDecimal vatRate, BigDecimal vatAmount, BigDecimal total) {}
}
