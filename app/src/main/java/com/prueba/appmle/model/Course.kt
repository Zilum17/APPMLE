package com.prueba.appmle.model

data class Course(
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val level: String,
    val image: String,
    val language: String,
    val price: Double,
    val isFree: Boolean,
    val subscription: Boolean,
    val duration: Int,
    val qualification: Double,
    val reviews: Int,
    val dateCreated: String,
    val dateUpdated: String,
    val status: String
)

