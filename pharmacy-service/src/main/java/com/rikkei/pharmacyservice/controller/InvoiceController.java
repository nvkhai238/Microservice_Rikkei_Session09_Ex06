package com.rikkei.pharmacyservice.controller;
import com.rikkei.pharmacyservice.entity.Invoice; import com.rikkei.pharmacyservice.service.InvoiceService;
import org.springframework.web.bind.annotation.*; import java.math.BigDecimal;
@RestController @RequestMapping("/api/v1/invoices") public class InvoiceController {
    private final InvoiceService invoiceService; public InvoiceController(InvoiceService invoiceService){this.invoiceService=invoiceService;}
    @PostMapping public Invoice create(@RequestBody InvoiceRequest request){return invoiceService.create(request.amount());}
    public record InvoiceRequest(BigDecimal amount) {}
}
