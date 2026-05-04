package com.proyectoinvdebienes.mobile.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.proyectoinvdebienes.mobile.model.AuthUser
import com.proyectoinvdebienes.mobile.model.LoginRequest
import com.proyectoinvdebienes.mobile.repository.AuthRepository

class LoginViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private val _loginState = MutableLiveData<LoginUiState>(LoginUiState.Idle)
    val loginState: LiveData<LoginUiState> = _loginState

    fun login(username: String, password: String) {
        if (username.isBlank() || password.isBlank()) {
            _loginState.value = LoginUiState.Error("Debe ingresar usuario y contraseña.")
            return
        }

        _loginState.value = LoginUiState.Loading
        authRepository.login(LoginRequest(username.trim(), password)) { result ->
            result
                .onSuccess { user -> _loginState.postValue(LoginUiState.Success(user)) }
                .onFailure { error -> _loginState.postValue(LoginUiState.Error(error.message ?: "Error desconocido")) }
        }
    }
}

sealed class LoginUiState {
    data object Idle : LoginUiState()
    data object Loading : LoginUiState()
    data class Success(val user: AuthUser) : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}
