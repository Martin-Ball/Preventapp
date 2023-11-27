package com.martin.preventapp.model

import com.martin.preventapp.model.entities.Request.LoginRequest
import com.martin.preventapp.model.entities.Response.LoginResponse
import com.martin.preventapp.model.entities.Response.TokenResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("auth/login")
    fun loginUser(
        @Body requestBody: LoginRequest
    ): Call<LoginResponse>

    @GET("auth/renewToken")
    fun token(
        @Header("x-token") token: String
    ): Call<TokenResponse>
}