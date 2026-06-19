package com.example.ordermanager.data.repository

import com.example.ordermanager.data.dao.OrderDao
import com.example.ordermanager.data.entity.OrderEntity

class OrderRepository(
    private val orderDao: OrderDao
) {

    val orders = orderDao.getAllOrdersWithDetails()

    suspend fun insert(order: OrderEntity) {
        orderDao.insert(order)
    }

    suspend fun update(order: OrderEntity) {
        orderDao.update(order)
    }

    suspend fun delete(order: OrderEntity) {
        orderDao.delete(order)
    }

    suspend fun deleteById(id: Int) {
        orderDao.deleteById(id)
    }
}