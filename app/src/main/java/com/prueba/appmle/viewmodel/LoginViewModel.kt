package com.prueba.appmle.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import kotlinx.coroutines.delay

class LoginViewModel: ViewModel() {
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email
    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }
    val isValidEmail: LiveData<Boolean> = _email.map { email ->
        isValidEmail(email)
    }

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password
    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }
    val isValidPassword: LiveData<Boolean> = _password.map { password ->
        isValidPassword(password)
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    private fun isValidPassword(password: String): Boolean = password.length > 6

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    suspend fun onLoginSelected() {
        _isLoading.value = true
        delay(4000)
        _isLoading.value = false
    }

}