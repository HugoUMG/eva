package com.proyectoinvdebienes.mobile.session

import android.content.Context
import android.util.Base64

class SessionManager(context: Context) {

    private val preferences = context.getSharedPreferences("inventory_mobile_session", Context.MODE_PRIVATE)

    fun saveCredentials(username: String, password: String) {
        preferences.edit()
            .putString(KEY_USERNAME, username)
            .putString(KEY_PASSWORD, password)
            .apply()
    }

    fun clear() {
        preferences.edit().clear().apply()
    }

    fun getBasicAuthHeader(): String? {
        val username = preferences.getString(KEY_USERNAME, null)
        val password = preferences.getString(KEY_PASSWORD, null)

        if (username.isNullOrBlank() || password.isNullOrBlank()) {
            return null
        }

        val token = Base64.encodeToString("$username:$password".toByteArray(), Base64.NO_WRAP)
        return "Basic $token"
    }

    companion object {
        private const val KEY_USERNAME = "username"
        private const val KEY_PASSWORD = "password"
    }
}
