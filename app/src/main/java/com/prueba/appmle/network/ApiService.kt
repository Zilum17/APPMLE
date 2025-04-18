package com.prueba.appmle.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    suspend fun postLogin(@Body request: LoginRequest): Response<LoginResponse>
}

object RetrofitClient {
    private const val BASE_URL = "http://192.168.0.16:3100/api/auth/"

    val apiService: ApiService by lazy {
        retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}