package com.rikkei.pharmacyservice.dto;
import java.math.BigDecimal;
public record InsuranceResponse(boolean verified, BigDecimal payableAmount, String note) {}
