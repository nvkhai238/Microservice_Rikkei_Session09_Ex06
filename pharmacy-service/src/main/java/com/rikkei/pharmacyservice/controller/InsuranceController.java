package com.rikkei.pharmacyservice.controller;
import com.rikkei.pharmacyservice.dto.InsuranceRequest; import com.rikkei.pharmacyservice.dto.InsuranceResponse; import com.rikkei.pharmacyservice.service.InsuranceService;
import org.springframework.web.bind.annotation.*; import java.util.concurrent.CompletableFuture;
@RestController @RequestMapping("/api/v1/insurance") public class InsuranceController {
    private final InsuranceService service; public InsuranceController(InsuranceService service){this.service=service;}
    @PostMapping("/validate") public CompletableFuture<InsuranceResponse> validate(@RequestBody InsuranceRequest request){return service.validate(request);}
}
