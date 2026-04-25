package com.proyectoinvdebienes.backend.web.dto;

import java.math.BigDecimal;

public record EmployeeAssignedAssetDto(
        Long employeeCode,
        String employeeName,
        String assignedAssetCode,
        String assetDescription,
        BigDecimal assetCost
) {
}
