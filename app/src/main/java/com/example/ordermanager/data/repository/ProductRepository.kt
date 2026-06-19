package com.example.ordermanager.data.repository

import com.example.ordermanager.data.dao.ProductDao
import com.example.ordermanager.data.entity.ProductEntity

class ProductRepository(
    private val productDao: ProductDao
) {
    val products = productDao.getAllProducts()

    suspend fun insert(product: ProductEntity) {
        productDao.insert(product)
    }

    suspend fun update(product: ProductEntity) {
        productDao.update(product)
    }

    suspend fun delete(product: ProductEntity) {
        productDao.delete(product)
    }
}