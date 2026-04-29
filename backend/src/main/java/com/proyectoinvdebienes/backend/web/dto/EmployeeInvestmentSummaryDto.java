package com.proyectoinvdebienes.backend.web.dto;

import java.math.BigDecimal;

public record EmployeeInvestmentSummaryDto(
        Long employeeCode,
        String employeeName,
        Long assignedAssetsCount,
        BigDecimal totalInvested
) {
}
