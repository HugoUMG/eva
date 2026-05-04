package com.proyectoinvdebienes.mobile.model

data class AuthUser(
    val username: String,
    val role: String,
    val employeeId: Long?
)
