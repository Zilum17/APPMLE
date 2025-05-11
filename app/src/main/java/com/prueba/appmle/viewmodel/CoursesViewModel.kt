package com.prueba.appmle.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prueba.appmle.model.Course
import com.prueba.appmle.network.LoginRequest
import com.prueba.appmle.network.RetrofitClient
import androidx.lifecycle.viewModelScope
import com.prueba.appmle.model.Lesson
import com.prueba.appmle.network.LessonsRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CoursesViewModel(): ViewModel() {
    private val _courses = MutableLiveData<List<Course>>(emptyList())
    val courses: LiveData<List<Course>> = _courses

    private val _lessons = MutableLiveData<List<Lesson>>(emptyList())
    val lessons: LiveData<List<Lesson>> = _lessons

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?> = _errorMessage
    fun getCourses(context: Context) {
        viewModelScope.launch{
            try {

                val token = getJwtToken(context)
                val response = RetrofitClient.apiService.getCourses("$token")
                if (response.isSuccessful) {
                    _courses.value = response.body()?.courses.orEmpty()
                } else {
                    _errorMessage.value = "Error: ${response.code()}"
                    println("Error: ${response.code()}")
                }
            } catch (e: Exception) {
                _errorMessage.value = "Connection error: ${e.message}"
                println("Connection error: ${e.message}")

            }
        }
    }
    fun getCourseById(courseId: String): Course? {
        return _courses.value?.find { it.id.toString() == courseId }
    }
    fun getLessons(courseId: String, context: Context) {
        viewModelScope.launch{
            val lessonsRequest = LessonsRequest(courseId)
            try {
                val token = getJwtToken(context)
                val response = RetrofitClient.apiService.getLessons("$token", lessonsRequest)
                if (response.isSuccessful) {
                    _lessons.value = response.body()?.lessons.orEmpty()
                } else {
                    _errorMessage.value = "Error: ${response.code()}"
                    println("Error: ${response.code()}")
                }
            } catch (e: Exception) {
                _errorMessage.value = "Connection error: ${e.message}"
                println("Connection error: ${e.message}")

            }
        }
    }
    fun clearLessons() {
        _lessons.value = emptyList()
    }
    fun getJwtToken(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("jwt_token", null)
    }
}