package com.prueba.appmle.model

data class Course(
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val level: String,
    val image: String,
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

data class Modules(
    val id: Int,
    val courseId: Int,
    val title: String,
    val description: String,
    val order: Int,
    val dateCreated: String,
    val dateUpdated: String
)

data class Lessons(
    val id: Int,
    val moduleId: Int,
    val title: String,
    val description: String,
    val video: String,
    val duration: Int,
    val order: Int,
    val dateCreated: String,
    val dateUpdated: String
)