package com.rikkei.pharmacyservice.service;
import com.rikkei.pharmacyservice.entity.Invoice;
import com.rikkei.pharmacyservice.repository.InvoiceRepository;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service public class InvoiceDeliveryService {
    private final InvoiceRepository repository;
    private final RestTemplate restTemplate;
    private final String billingUrl;
    public InvoiceDeliveryService(InvoiceRepository repository, RestTemplate restTemplate,
            @Value("${billing-service.url}") String billingUrl) {
        this.repository=repository; this.restTemplate=restTemplate; this.billingUrl=billingUrl;
    }
    @Retry(name="invoiceRetry", fallbackMethod="exportFallback")
    @RateLimiter(name="invoiceLimit", fallbackMethod="exportFallback")
    public Invoice export(Invoice invoice) {
        restTemplate.postForObject(billingUrl + "/api/v1/e-invoices", invoice, String.class);
        invoice.setStatus("SENT"); return repository.save(invoice);
    }
    public Invoice exportFallback(Invoice invoice, Throwable ex) {
        invoice.setStatus("PENDING"); return repository.save(invoice);
    }
}
