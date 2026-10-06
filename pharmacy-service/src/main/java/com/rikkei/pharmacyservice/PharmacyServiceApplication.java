package com.rikkei.pharmacyservice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;
import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
@SpringBootApplication public class PharmacyServiceApplication {
    private static final Logger log = LoggerFactory.getLogger(PharmacyServiceApplication.class);
    public static void main(String[] args) { SpringApplication.run(PharmacyServiceApplication.class, args); }
    @Bean RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.setConnectTimeout(Duration.ofSeconds(1))
                .setReadTimeout(Duration.ofSeconds(2)).build();
    }
    @Bean ApplicationRunner branchInfo(@Value("${app.branch-name:Chưa có cấu hình}") String branch,
            @Value("${app.hotline:Chưa có cấu hình}") String hotline) {
        return args -> log.info("Chi nhánh: {}, hotline: {}", branch, hotline);
    }
}
