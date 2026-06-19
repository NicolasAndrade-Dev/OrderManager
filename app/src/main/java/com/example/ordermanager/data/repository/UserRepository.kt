package com.example.ordermanager.data.repository

import com.example.ordermanager.data.dao.UserDao
import com.example.ordermanager.data.entity.UserEntity
import kotlinx.coroutines.flow.Flow

class UserRepository(
    private val userDao: UserDao
) {

    val users: Flow<List<UserEntity>> =
        userDao.getAllUsers()

    suspend fun login(
        username: String,
        password: String
    ): UserEntity? {
        return userDao.login(username, password)
    }

    suspend fun createUser(
        username: String,
        password: String,
        role: String
    ) {
        userDao.insertUser(
            UserEntity(
                username = username,
                password = password,
                role = role
            )
        )
    }

    suspend fun deleteUser(id: Int) {
        userDao.deleteUser(id)
    }

    suspend fun createDefaultUser() {

        val existingUser =
            userDao.getUserByUsername("admin")

        if (existingUser == null) {

            userDao.insertUser(
                UserEntity(
                    username = "admin",
                    password = "123",
                    role = "ADMIN"
                )
            )
        }
    }
}