package com.proyectoinvdebienes.mobile.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import com.proyectoinvdebienes.mobile.R
import com.proyectoinvdebienes.mobile.api.ApiClient
import com.proyectoinvdebienes.mobile.model.EmployeeAssignedAssetDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val employeeInput = findViewById<EditText>(R.id.employeeIdInput)
        val fetchButton = findViewById<Button>(R.id.fetchButton)
        val output = findViewById<TextView>(R.id.outputText)

        fetchButton.setOnClickListener {
            val employeeId = employeeInput.text.toString().toLongOrNull()
            if (employeeId == null) {
                output.text = "Ingrese un código de empleado válido."
                return@setOnClickListener
            }

            output.text = "Consultando..."
            ApiClient.reportsApi.getAssignedAssetsByEmployee(employeeId)
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
