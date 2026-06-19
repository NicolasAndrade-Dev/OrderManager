package com.example.ordermanager.data.entity

data class OrderWithDetails(
    val id: Int,
    val clientId: Int,
    val productId: Int,
    val clientName: String,
    val productName: String,
    val quantity: Int,
    val orderDate: String,
    val orderTime: String,
    val totalValue: Double
)