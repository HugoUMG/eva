package com.proyectoinvdebienes.mobile.model

data class EmployeeAssignedAssetDto(
    val employeeCode: Long,
    val employeeName: String,
    val assignedAssetCode: String,
    val assetDescription: String,
    val assetCost: Double
)
