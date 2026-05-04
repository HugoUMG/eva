package com.proyectoinvdebienes.mobile.api

import com.proyectoinvdebienes.mobile.model.AuthUser
import retrofit2.Call
import retrofit2.http.GET

interface AuthApi {
    @GET("api/auth/me")
    fun me(): Call<AuthUser>
}
