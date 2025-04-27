package com.prueba.appmle.network

import com.prueba.appmle.model.Course

data class CoursesResponse(
    val success: Boolean,
    val courses: List<Course>,
    val message: String
)