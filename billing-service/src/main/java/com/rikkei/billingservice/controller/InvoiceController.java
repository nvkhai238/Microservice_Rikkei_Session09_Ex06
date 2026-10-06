package com.rikkei.billingservice.controller;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/v1/e-invoices") public class InvoiceController {
    @PostMapping public String submit(@RequestBody Object invoice) { return "accepted"; }
}
