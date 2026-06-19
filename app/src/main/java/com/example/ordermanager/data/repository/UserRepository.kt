package com.example.ordermanager.data.repository

import com.example.ordermanager.data.dao.UserDao
import com.example.ordermanager.data.entity.UserEntity

class UserRepository(
    private val userDao: UserDao
) {

    suspend fun login(username: String, password: String): UserEntity? {
        return userDao.login(username, password)
    }

    suspend fun createDefaultUser() {
        val existingUser = userDao.getUserByUsername("admin")

        if (existingUser == null) {
            userDao.insertUser(
                UserEntity(
                    username = "admin",
                    password = "123"
                )
            )
        }
    }
}