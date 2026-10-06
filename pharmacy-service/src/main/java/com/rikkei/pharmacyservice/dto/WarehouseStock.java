package com.rikkei.pharmacyservice.dto;
public record WarehouseStock(Long medicineId, int quantity, boolean localData, String message) {}
