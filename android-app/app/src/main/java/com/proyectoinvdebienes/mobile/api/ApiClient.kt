package com.proyectoinvdebienes.mobile.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    // Cambiar por la IP local de tu máquina cuando pruebes en teléfono real.
    private const val BASE_URL = "http://10.0.2.2:8080/"

    val reportsApi: ReportsApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ReportsApi::class.java)
    }
}
