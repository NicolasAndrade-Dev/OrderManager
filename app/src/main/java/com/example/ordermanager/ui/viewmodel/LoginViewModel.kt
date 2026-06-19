package com.example.ordermanager.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ordermanager.data.entity.UserEntity
import com.example.ordermanager.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: UserRepository
) : ViewModel() {

    private val _loginSuccess = MutableStateFlow(false)
    val loginSuccess: StateFlow<Boolean> = _loginSuccess

    private val _loggedUser = MutableStateFlow<UserEntity?>(null)
    val loggedUser: StateFlow<UserEntity?> = _loggedUser

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        viewModelScope.launch {
            repository.createDefaultUser()
        }
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            if (username.isBlank() || password.isBlank()) {
                _errorMessage.value = "Preencha usuário e senha"
                return@launch
            }

            val user = repository.login(username, password)

            if (user != null) {
                _loggedUser.value = user
                _loginSuccess.value = true
            } else {
                _errorMessage.value = "Usuário ou senha inválidos"
            }
        }
    }

    fun clearError() {
        _errorMessage.value = null
    }
}