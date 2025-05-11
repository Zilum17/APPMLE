package com.prueba.appmle.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("auth/login")
    suspend fun postLogin(@Body request: LoginRequest): Response<LoginResponse>

    @POST("auth/register")
    suspend fun postRegister(@Body request: RegisterRequest): Response<LoginResponse>

    @POST("auth/verify")
    suspend fun postVerify(@Body request: VerifyTokenRequest): Response<LoginResponse>

    @GET("courses/get")
    suspend fun getCourses(@Header("Authorization") token: String): Response<CoursesResponse>

    @POST("lessons/get")
    suspend fun getLessons(@Header("Authorization") token: String, @Body request: LessonsRequest): Response<LessonsResponse>
}

object RetrofitClient {
    private const val BASE_URL = "http://192.168.0.16:3100/api/"

    val apiService: ApiService by lazy {
        retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}