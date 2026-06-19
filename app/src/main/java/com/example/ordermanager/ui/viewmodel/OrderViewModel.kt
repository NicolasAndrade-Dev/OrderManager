package com.example.ordermanager.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ordermanager.data.entity.OrderEntity
import com.example.ordermanager.data.repository.OrderRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class OrderViewModel(
    private val repository: OrderRepository
) : ViewModel() {

    val orders = repository.orders.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun insert(
        clientId: Int,
        productId: Int,
        quantity: Int,
        orderDate: String,
        orderTime: String,
        totalValue: Double
    ) {
        viewModelScope.launch {
            repository.insert(
                OrderEntity(
                    clientId = clientId,
                    productId = productId,
                    quantity = quantity,
                    orderDate = orderDate,
                    orderTime = orderTime,
                    totalValue = totalValue
                )
            )
        }
    }

    fun update(order: OrderEntity) {
        viewModelScope.launch {
            repository.update(order)
        }
    }

    fun delete(order: OrderEntity) {
        viewModelScope.launch {
            repository.delete(order)
        }
    }

    fun deleteById(id: Int) {
        viewModelScope.launch {
            repository.deleteById(id)
        }
    }
}