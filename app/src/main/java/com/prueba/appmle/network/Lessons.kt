package com.prueba.appmle.network

import com.prueba.appmle.model.Lesson

data class LessonsResponse(
    val success: Boolean,
    val lessons: List<Lesson>,
    val message: String
)

data class LessonsRequest(
    val idCourse: String
)