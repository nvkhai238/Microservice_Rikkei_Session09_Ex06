package com.rikkei.insuranceservice.controller;
import org.springframework.beans.factory.annotation.Value; import org.springframework.web.bind.annotation.*; import java.util.concurrent.TimeUnit;
@RestController @RequestMapping("/api/v1/insurance") public class InsuranceController {
    @Value("${insurance-service.response-delay:0}") private long delay;
    @PostMapping("/validate") public String validate(@RequestBody Object request) throws InterruptedException { TimeUnit.MILLISECONDS.sleep(delay); return "OK"; }
}
