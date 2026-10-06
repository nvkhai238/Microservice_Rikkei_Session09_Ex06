package com.rikkei.pharmacyservice.controller;
import com.rikkei.pharmacyservice.dto.WarehouseStock; import com.rikkei.pharmacyservice.service.WarehouseClientService; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/v1/warehouse/medicines") public class WarehouseController {
    private final WarehouseClientService warehouseClientService;
    public WarehouseController(WarehouseClientService warehouseClientService) { this.warehouseClientService=warehouseClientService; }
    @GetMapping("/{id}/check") public WarehouseStock check(@PathVariable Long id) { return warehouseClientService.check(id); }
}
