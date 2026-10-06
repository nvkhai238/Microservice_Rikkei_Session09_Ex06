package com.rikkei.pharmacyservice.service;
import com.rikkei.pharmacyservice.entity.Invoice;
import com.rikkei.pharmacyservice.repository.InvoiceRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
@Service public class InvoiceService {
    private final InvoiceRepository repository;
    private final InvoiceDeliveryService deliveryService;
    public InvoiceService(InvoiceRepository repository, InvoiceDeliveryService deliveryService) {
        this.repository=repository; this.deliveryService=deliveryService;
    }
    public Invoice create(BigDecimal amount) {
        return deliveryService.export(repository.save(new Invoice(amount)));
    }
}
