package com.example.ordermanager.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ordermanager.data.entity.ClientEntity
import com.example.ordermanager.data.repository.ClientRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ClientViewModel(
    private val repository: ClientRepository
) : ViewModel() {

    val clients = repository.clients.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun insert(name: String, phone: String, email: String, city: String) {
        viewModelScope.launch {
            repository.insert(
                ClientEntity(
                    name = name,
                    phone = phone,
                    email = email,
                    city = city
                )
            )
        }
    }

    fun update(client: ClientEntity) {
        viewModelScope.launch {
            repository.update(client)
        }
    }

    fun delete(client: ClientEntity) {
        viewModelScope.launch {
            repository.delete(client)
        }
    }
}