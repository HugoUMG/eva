package com.proyectoinvdebienes.mobile.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import com.proyectoinvdebienes.mobile.R
import com.proyectoinvdebienes.mobile.api.ApiClient
import com.proyectoinvdebienes.mobile.model.EmployeeAssignedAssetDto
import com.proyectoinvdebienes.mobile.repository.AuthRepository
import com.proyectoinvdebienes.mobile.session.SessionManager
import com.proyectoinvdebienes.mobile.ui.login.LoginUiState
import com.proyectoinvdebienes.mobile.ui.login.LoginViewModel
import com.proyectoinvdebienes.mobile.ui.login.LoginViewModelFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(
            AuthRepository(
                ApiClient.authApi(this),
                SessionManager(this)
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val usernameInput = findViewById<EditText>(R.id.usernameInput)
        val passwordInput = findViewById<EditText>(R.id.passwordInput)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val loginResult = findViewById<TextView>(R.id.loginResult)
        val securedSection = findViewById<View>(R.id.securedSection)

        val employeeInput = findViewById<EditText>(R.id.employeeIdInput)
        val fetchButton = findViewById<Button>(R.id.fetchButton)
        val output = findViewById<TextView>(R.id.outputText)

        loginViewModel.loginState.observe(this) { state ->
            when (state) {
                is LoginUiState.Idle -> loginResult.text = "Estado: pendiente"
                is LoginUiState.Loading -> loginResult.text = "Autenticando..."
                is LoginUiState.Success -> {
                    loginResult.text = "Login correcto: ${state.user.username} (${state.user.role})"
                    securedSection.visibility = View.VISIBLE
                }
                is LoginUiState.Error -> {
                    loginResult.text = "No se pudo iniciar sesión: ${state.message}"
                    securedSection.visibility = View.GONE
                }
            }
        }

        loginButton.setOnClickListener {
            loginViewModel.login(
                usernameInput.text.toString(),
                passwordInput.text.toString()
            )
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
                    override fun onResponse(call: Call<List<EmployeeAssignedAssetDto>>, response: Response<List<EmployeeAssignedAssetDto>>) {
                        if (response.isSuccessful) {
                            val gson = GsonBuilder().setPrettyPrinting().create()
                            output.text = gson.toJson(response.body().orEmpty())
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
