package com.example.ordermanager.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ordermanager.data.entity.ProductEntity
import com.example.ordermanager.data.repository.ProductRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductViewModel(
    private val repository: ProductRepository
) : ViewModel() {

    val products = repository.products.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun insert(name: String, description: String, price: Double, stockQuantity: Int) {
        viewModelScope.launch {
            repository.insert(
                ProductEntity(
                    name = name,
                    description = description,
                    price = price,
                    stockQuantity = stockQuantity
                )
            )
        }
    }

    fun update(product: ProductEntity) {
        viewModelScope.launch {
            repository.update(product)
        }
    }

    fun delete(product: ProductEntity) {
        viewModelScope.launch {
            repository.delete(product)
        }
    }
}