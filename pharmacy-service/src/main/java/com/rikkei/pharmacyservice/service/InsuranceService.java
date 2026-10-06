package com.rikkei.pharmacyservice.service;

import com.rikkei.pharmacyservice.dto.InsuranceRequest;
import com.rikkei.pharmacyservice.dto.InsuranceResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

@RefreshScope
@Service
public class InsuranceService {
    private final InsuranceRetryService retryService;
    private final Duration timeout;
    private final BigDecimal discountRate;

    public InsuranceService(InsuranceRetryService retryService,
                            @Value("${pharmacy.insurance.timeout:3s}") Duration timeout,
            @Value("${pharmacy.insurance.discount-rate:0.10}") BigDecimal discountRate) {
        this.retryService = retryService;
        this.timeout = timeout;
        this.discountRate = discountRate;
    }

    @CircuitBreaker(name = "insuranceCB", fallbackMethod = "insuranceFallback")
    public CompletableFuture<InsuranceResponse> validate(InsuranceRequest request) {
        return retryService.validate(request, timeout, discountRate);
    }

    public CompletableFuture<InsuranceResponse> insuranceFallback(InsuranceRequest request, Throwable ex) {
        BigDecimal originalPrice = request.originalPrice();
        return CompletableFuture.completedFuture(
                new InsuranceResponse(false, originalPrice, "Xác thực bảo hiểm sau"));
    }
}
