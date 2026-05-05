package com.proyectoinvdebienes.mobile.repository

import com.proyectoinvdebienes.mobile.api.AuthApi
import com.proyectoinvdebienes.mobile.model.AuthUser
import com.proyectoinvdebienes.mobile.model.LoginRequest
import com.proyectoinvdebienes.mobile.session.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository(
    private val authApi: AuthApi,
    private val sessionManager: SessionManager
) {

    fun login(request: LoginRequest, onResult: (Result<AuthUser>) -> Unit) {
        sessionManager.saveCredentials(request.username, request.password)

        authApi.me().enqueue(object : Callback<AuthUser> {
            override fun onResponse(call: Call<AuthUser>, response: Response<AuthUser>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        onResult(Result.success(it))
                    } ?: run {
                        sessionManager.clear()
                        onResult(Result.failure(IllegalStateException("Respuesta sin usuario")))
                    }
                } else {
                    sessionManager.clear()
                    onResult(Result.failure(IllegalStateException("Credenciales inválidas (${response.code()})")))
                }
            }

            override fun onFailure(call: Call<AuthUser>, t: Throwable) {
                sessionManager.clear()
                onResult(Result.failure(t))
            }
        })
    }
}
