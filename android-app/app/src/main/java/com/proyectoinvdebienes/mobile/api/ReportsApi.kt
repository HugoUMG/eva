package com.proyectoinvdebienes.mobile.api

import com.proyectoinvdebienes.mobile.model.EmployeeAssignedAssetDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ReportsApi {
    @GET("api/reports/employee/{employeeId}/assigned-assets")
    fun getAssignedAssetsByEmployee(
        @Path("employeeId") employeeId: Long
    ): Call<List<EmployeeAssignedAssetDto>>
}
