package com.prueba.appmle.viewmodel

import android.content.Context
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.prueba.appmle.model.User
import com.prueba.appmle.network.RetrofitClient
import com.prueba.appmle.network.LoginRequest
import androidx.core.content.edit
import androidx.lifecycle.viewModelScope
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    // Variables para email: Login
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email
    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }
    val isValidEmail: LiveData<Boolean> = _email.map { email ->
        isValidEmail(email)
    }
    // Variables para password: Login
    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password
    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }
    val isValidPassword: LiveData<Boolean> = _password.map { password ->
        isValidPassword(password)
    }
    // Variable para loading
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    // Variable para errorMessage
    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?> = _errorMessage
    // Funcion para validar contraseña mayor a 6 caracteres
    private fun isValidPassword(password: String): Boolean = password.length > 6
    // Funcion para validar email segun Patterns
    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    // Varable para el usuario
    private val _userData = MutableLiveData<User>()
    val userData: LiveData<User> = _userData
    // Variable para el token
    private val _jwtToken = MutableLiveData<String?>(null)
    val jwtToken: String? get() = _jwtToken.value
    // Funcion guardar token de rom
    fun saveJwtToken(context: Context, token: String) {
        val sharedPreferences = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
        sharedPreferences.edit() { putString("jwt_token", token) }
        _jwtToken.value = token
    }
    // Funcion obtener token de rom
    fun getJwtToken(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("jwt_token", null).also { token ->
            _jwtToken.value = token
        }
    }
    // Funcion para iniciar sesion
    suspend fun login(context: Context): Boolean {
        _isLoading.value = true
        _errorMessage.value = null
        try {
            val response = RetrofitClient.apiService.postLogin(
                LoginRequest(
                    email = _email.value.toString(),
                    password = _password.value.toString()
                )
            )

            if (response.isSuccessful) {
                _userData.value = response.body()?.user
                saveJwtToken(context, _jwtToken.value.toString())
                return true
            } else {
                _errorMessage.value = "Error: ${response.code()}"
                println("Error: ${response.code()}")
            }
        } catch (e: Exception) {
            _errorMessage.value = "Connection error: ${e.message}"
            println("Connection error: ${e.message}")

        } finally {
            _isLoading.value = false
        }
        return false
    }
    // Funcion para terminar sesion
    fun logout(context: Context) {
        viewModelScope.launch {
            val sharedPrefs = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
            sharedPrefs.edit() { clear() }
        }
    }

}