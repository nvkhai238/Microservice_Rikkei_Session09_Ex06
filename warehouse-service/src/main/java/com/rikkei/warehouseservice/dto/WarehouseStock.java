package com.rikkei.warehouseservice.dto;
public record WarehouseStock(Long medicineId, int quantity, boolean localData, String message) {}
