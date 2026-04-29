package com.proyectoinvdebienes.mobile.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import com.proyectoinvdebienes.mobile.R
import com.proyectoinvdebienes.mobile.api.ApiClient
import com.proyectoinvdebienes.mobile.model.EmployeeAssignedAssetDto
import com.proyectoinvdebienes.mobile.model.LoginRequest
import com.proyectoinvdebienes.mobile.repository.AuthRepository
import com.proyectoinvdebienes.mobile.session.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val usernameInput = findViewById<EditText>(R.id.usernameInput)
        val passwordInput = findViewById<EditText>(R.id.passwordInput)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val loginResult = findViewById<TextView>(R.id.loginResult)

        val employeeInput = findViewById<EditText>(R.id.employeeIdInput)
        val fetchButton = findViewById<Button>(R.id.fetchButton)
        val output = findViewById<TextView>(R.id.outputText)

        val securedSection = findViewById<View>(R.id.securedSection)
        val sessionManager = SessionManager(this)
        val authRepository = AuthRepository(ApiClient.authApi(this), sessionManager)

        loginButton.setOnClickListener {
            val username = usernameInput.text.toString().trim()
            val password = passwordInput.text.toString()

            if (username.isBlank() || password.isBlank()) {
                loginResult.text = "Debe ingresar usuario y contraseña."
                return@setOnClickListener
            }

            loginResult.text = "Autenticando..."
            authRepository.login(LoginRequest(username, password)) { result ->
                runOnUiThread {
                    result
                        .onSuccess { user ->
                            loginResult.text = "Login correcto: ${user.username} (${user.role})"
                            securedSection.visibility = View.VISIBLE
                        }
                        .onFailure { error ->
                            loginResult.text = "No se pudo iniciar sesión: ${error.message}"
                            securedSection.visibility = View.GONE
                        }
                }
            }
        }

        fetchButton.setOnClickListener {
            val employeeId = employeeInput.text.toString().toLongOrNull()
            if (employeeId == null) {
                output.text = "Ingrese un código de empleado válido."
                return@setOnClickListener
            }

            output.text = "Consultando..."
            ApiClient.reportsApi(this).getAssignedAssetsByEmployee(employeeId)
                .enqueue(object : Callback<List<EmployeeAssignedAssetDto>> {
                    override fun onResponse(
                        call: Call<List<EmployeeAssignedAssetDto>>,
                        response: Response<List<EmployeeAssignedAssetDto>>
                    ) {
                        if (response.isSuccessful) {
                            val data = response.body().orEmpty()
                            val gson = GsonBuilder().setPrettyPrinting().create()
                            output.text = gson.toJson(data)
                        } else {
                            output.text = "Error HTTP: ${response.code()}"
                        }
                    }

                    override fun onFailure(call: Call<List<EmployeeAssignedAssetDto>>, t: Throwable) {
                        output.text = "Error de red: ${t.message}"
                    }
                })
        }
    }
}
