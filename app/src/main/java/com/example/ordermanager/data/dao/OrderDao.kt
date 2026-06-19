package com.example.ordermanager.data.dao

import androidx.room.*
import com.example.ordermanager.data.entity.OrderEntity
import com.example.ordermanager.data.entity.OrderWithDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {

    @Insert
    suspend fun insert(order: OrderEntity)

    @Update
    suspend fun update(order: OrderEntity)

    @Delete
    suspend fun delete(order: OrderEntity)

    @Query("""
        SELECT
            orders.id,
            orders.clientId,
            orders.productId,
            clients.name AS clientName,
            products.name AS productName,
            orders.quantity,
            orders.orderDate,
            orders.orderTime,
            orders.totalValue
        FROM orders
        INNER JOIN clients
            ON orders.clientId = clients.id
        INNER JOIN products
            ON orders.productId = products.id
        ORDER BY orders.id DESC
    """)
    fun getAllOrdersWithDetails(): Flow<List<OrderWithDetails>>

    @Query("SELECT * FROM orders WHERE id = :id")
    suspend fun getOrderById(id: Int): OrderEntity?

    @Query("DELETE FROM orders WHERE id = :id")
    suspend fun deleteById(id: Int)
}