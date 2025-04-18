package com.prueba.appmle.network

import com.prueba.appmle.model.User

data class LoginResponse(
    val success: Boolean,
    val user: User,
    val jwt: String,
    val message: String
)

data class LoginRequest(
    val email: String,
    val password: String
)
