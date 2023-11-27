package com.martin.preventapp.model

import com.martin.preventapp.model.entities.Request.LoginRequest
import com.martin.preventapp.model.entities.Response.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("auth/login")
    fun loginUser(
        @Body requestBody: LoginRequest
    ): Call<LoginResponse>
}