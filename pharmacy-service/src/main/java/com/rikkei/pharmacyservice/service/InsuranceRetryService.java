package com.rikkei.pharmacyservice.service;

import com.rikkei.pharmacyservice.dto.InsuranceRequest;
import com.rikkei.pharmacyservice.dto.InsuranceResponse;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Service
public class InsuranceRetryService {
    private static final ScheduledExecutorService TIMER = Executors.newSingleThreadScheduledExecutor(task -> {
        Thread thread = new Thread(task, "insurance-timeout");
        thread.setDaemon(true);
        return thread;
    });

    private final RestTemplate restTemplate;
    private final String insuranceUrl;

    public InsuranceRetryService(RestTemplate restTemplate,
                                 @Value("${insurance-service.url}") String insuranceUrl) {
        this.restTemplate = restTemplate;
        this.insuranceUrl = insuranceUrl;
    }

    @Retry(name = "insuranceRetry")
    public CompletableFuture<InsuranceResponse> validate(InsuranceRequest request, Duration timeout, BigDecimal discountRate) {
        TimeLimiter limiter = TimeLimiter.of(TimeLimiterConfig.custom()
                .timeoutDuration(timeout).cancelRunningFuture(true).build());
        return limiter.executeCompletionStage(TIMER, () -> CompletableFuture.supplyAsync(() -> {
            restTemplate.postForObject(insuranceUrl + "/api/v1/insurance/validate", request, Object.class);
            BigDecimal payable = request.originalPrice().multiply(BigDecimal.ONE.subtract(discountRate));
            return new InsuranceResponse(true, payable, "Đã xác thực bảo hiểm");
        })).toCompletableFuture();
    }
}
