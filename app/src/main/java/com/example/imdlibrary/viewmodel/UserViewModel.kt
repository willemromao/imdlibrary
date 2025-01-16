package com.example.imdlibrary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdlibrary.data.model.User
import com.example.imdlibrary.data.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    var currentUser: User? = null
        private set

    fun login(username: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val user = repository.login(username, password)
            if (user != null) {
                currentUser = user
                onResult(true)
            } else {
                onResult(false)
            }
        }
    }

    fun register(username: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            if (repository.getUserByUsername(username) == null) {
                repository.insertUser(User(username, password))
                onResult(true)
            } else {
                onResult(false)
            }
        }
    }

    fun resetPassword(username: String, newPassword: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val user = repository.getUserByUsername(username)
            if (user != null) {
                val updatedUser = user.copy(password = newPassword)
                repository.updateUser(updatedUser)
                onResult(true)
            } else {
                onResult(false)
            }
        }
    }
}
