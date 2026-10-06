package com.rikkei.pharmacyservice.dto;
import java.math.BigDecimal;
public record InsuranceRequest(Long patientId, BigDecimal originalPrice) {}
