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
import com.prueba.appmle.network.RegisterRequest
import com.prueba.appmle.network.VerifyTokenRequest
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
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
    // Varable para el usuario
    private val _userData = MutableLiveData<User>(null)
    val userData: LiveData<User> = _userData
    // Variable para el token
    private val _jwtToken = MutableLiveData<String?>(null)
    val jwtToken: String? get() = _jwtToken.value
    // Funcion guardar token de rom
    fun saveJwtToken(context: Context, token: String) {
        val sharedPreferences = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
        sharedPreferences.edit { putString("jwt_token", token) }
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
                _jwtToken.value = response.body()?.jwt
                saveJwtToken(context, jwtToken.toString())
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
    // Variables para nombre: Register
    private val _firstNameR = MutableLiveData<String>()
    val firstNameR: LiveData<String> = _firstNameR
    fun updateFirstNameR(newFirstName: String) {
        _firstNameR.value = newFirstName
    }
    val isValidFirstNameR: LiveData<Boolean> = _firstNameR.map { firstName ->
        isValidFirstNameR(firstName)
    }
    // Variables para apellido: Register
    private val _lastNameR = MutableLiveData<String>()
    val lastNameR: LiveData<String> = _lastNameR
    fun updateLastNameR(newLastName: String) {
        _lastNameR.value = newLastName
    }
    val isValidLastNameR: LiveData<Boolean> = _lastNameR.map { lastName ->
        isValidLastNameR(lastName)
    }
    // Variables para email: Register
    private val _emailR = MutableLiveData<String>()
    val emailR: LiveData<String> = _emailR
    fun updateEmailR(newEmail: String) {
        _emailR.value = newEmail
    }
    val isValidEmailR: LiveData<Boolean> = _emailR.map { email ->
        isValidEmail(email)
    }
    // Variables para password: Register
    private val _passwordR = MutableLiveData<String>()
    val passwordR: LiveData<String> = _passwordR
    fun updatePasswordR(newPassword: String) {
        _passwordR.value = newPassword
    }
    val isValidPasswordR: LiveData<Boolean> = _passwordR.map { password ->
        isValidPassword(password)
    }
    // Variables para password repetida: Register
    private val _passwordCR = MutableLiveData<String>()
    val passwordCR: LiveData<String> = _passwordCR
    fun updatePasswordCR(newPassword: String) {
        _passwordCR.value = newPassword
    }
    val isValidPasswordCR: LiveData<Boolean> = _passwordCR.map { password ->
        isValidPasswordCR()
    }
    private val _tokenVerificationResult = MutableLiveData<Boolean>(null)
    val tokenVerificationResult: LiveData<Boolean> get() = _tokenVerificationResult
    // Funcion para validar nombre
    private fun isValidFirstNameR(name: String): Boolean {
        val pattern = "^[\\p{L} \\-']+$".toRegex()
        println(name.trim().matches(pattern) && name.length in 2..50)
        return name.trim().matches(pattern) && name.length in 2..50
    }
    // Funcion para validar apellido
    private fun isValidLastNameR(name: String): Boolean {
        val pattern = "^[\\p{L} \\-']+$".toRegex()
        return name.trim().matches(pattern) && name.length in 2..50
    }
    // Funcion para validar contraseña mayor a 6 caracteres
    private fun isValidPassword(password: String): Boolean = password.length > 6
    // Funcion para validar email segun Patterns
    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    // Funcion para validar contraseña repetida
    private fun isValidPasswordCR(): Boolean {
        return passwordR.value == passwordCR.value
    }
    // Funcion para registrarse e iniciar sesion
    suspend fun register(context: Context): Boolean {
        _isLoading.value = true
        _errorMessage.value = null
        try {
            val response = RetrofitClient.apiService.postRegister(
                RegisterRequest(
                    firstName = _firstNameR.value.toString(),
                    lastName = _lastNameR.value.toString(),
                    email = _emailR.value.toString(),
                    password = _passwordR.value.toString()
                )
            )
            if (response.isSuccessful) {
                _userData.value = response.body()?.user
                _jwtToken.value = response.body()?.jwt
                println(jwtToken.toString())
                println(userData.value)
                saveJwtToken(context, jwtToken.toString())
                return true
            } else {
                _errorMessage.value = "Error: ${response.code()}"
                println("Error: ${response.code()}")
                return false
            }
        } catch (e: Exception) {
            _errorMessage.value = "Connection error: ${e.message}"
            println("Connection error: ${e.message}")

        } finally {
            _isLoading.value = false
        }
        return false
    }
     fun verifyToken(context: Context) {
         viewModelScope.launch {
             getJwtToken(context)
             _isLoading.value = true
            try {
                val response = RetrofitClient.apiService.postVerify(
                    VerifyTokenRequest(
                        token = _jwtToken.value.toString()
                    )
                )
                if (response.isSuccessful) {
                    _userData.value = response.body()?.user
                    _tokenVerificationResult.value = true
                } else {
                    _errorMessage.value = "Error: ${response.code()}"
                    println("Error: ${response.code()}")
                    _tokenVerificationResult.value = false
                }
            } catch (e: Exception) {
                _errorMessage.value = "Connection error: ${e.message}"
                println("Connection error: ${e.message}")
                _tokenVerificationResult.value = false
            }
             _isLoading.value = false
         }

    }

    // Funcion para terminar sesion
    fun logout(context: Context) {
        viewModelScope.launch {
            val sharedPrefs = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
            sharedPrefs.edit { clear() }
        }
    }

}