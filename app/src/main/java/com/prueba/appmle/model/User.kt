package com.prueba.appmle.model


data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val dateRegister: String,
    val lastLogin: String,
    val active: Boolean,
)
