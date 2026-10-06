package com.rikkei.warehouseservice.controller;
import com.rikkei.warehouseservice.dto.WarehouseStock; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/v1/warehouse/medicines") public class WarehouseController {
    @GetMapping("/{id}") public WarehouseStock check(@PathVariable Long id) { return new WarehouseStock(id, 25, false, "Tồn kho tổng"); }
}
