package com.example.imdlibrary.data.repository

import com.example.imdlibrary.data.database.UserDao
import com.example.imdlibrary.data.model.User

class UserRepository(private val userDao: UserDao) {
    suspend fun insertUser(user: User) = userDao.insertUser(user)
    suspend fun login(username: String, password: String) = userDao.login(username, password)
    suspend fun getUserByUsername(username: String) = userDao.getUserByUsername(username)
    suspend fun updateUser(user: User) = userDao.updateUser(user) // Atualiza o usuário
}
