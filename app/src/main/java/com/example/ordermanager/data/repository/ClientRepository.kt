package com.example.ordermanager.data.repository

import com.example.ordermanager.data.dao.ClientDao
import com.example.ordermanager.data.entity.ClientEntity

class ClientRepository(
    private val clientDao: ClientDao
) {
    val clients = clientDao.getAllClients()

    suspend fun insert(client: ClientEntity) {
        clientDao.insert(client)
    }

    suspend fun update(client: ClientEntity) {
        clientDao.update(client)
    }

    suspend fun delete(client: ClientEntity) {
        clientDao.delete(client)
    }
}