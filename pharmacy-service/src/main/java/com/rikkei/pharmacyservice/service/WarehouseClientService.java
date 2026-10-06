package com.rikkei.pharmacyservice.service;
import com.rikkei.pharmacyservice.dto.WarehouseStock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
@Service public class WarehouseClientService {
    private final RestTemplate restTemplate; private final String baseUrl;
    public WarehouseClientService(RestTemplate restTemplate, @Value("${warehouse-service.url}") String baseUrl) { this.restTemplate=restTemplate; this.baseUrl=baseUrl; }
    @CircuitBreaker(name = "warehouseCB", fallbackMethod = "checkWarehouseFallback")
    public WarehouseStock check(Long medicineId) {
        return restTemplate.getForObject(baseUrl + "/api/v1/warehouse/medicines/{id}", WarehouseStock.class, medicineId);
    }
    public WarehouseStock checkWarehouseFallback(Long medicineId, Throwable ex) {
        return new WarehouseStock(medicineId, 3, true,
                "Không thể kết nối kho tổng. Hệ thống sẽ sử dụng dữ liệu tồn kho cục bộ để tiếp tục giao dịch");
    }
}
