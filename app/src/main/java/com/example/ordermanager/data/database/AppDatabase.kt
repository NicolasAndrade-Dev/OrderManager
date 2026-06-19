package com.example.ordermanager.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ordermanager.data.dao.ClientDao
import com.example.ordermanager.data.dao.OrderDao
import com.example.ordermanager.data.dao.ProductDao
import com.example.ordermanager.data.dao.UserDao
import com.example.ordermanager.data.entity.ClientEntity
import com.example.ordermanager.data.entity.OrderEntity
import com.example.ordermanager.data.entity.ProductEntity
import com.example.ordermanager.data.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
        ClientEntity::class,
        ProductEntity::class,
        OrderEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun clientDao(): ClientDao

    abstract fun productDao(): ProductDao

    abstract fun orderDao(): OrderDao
}