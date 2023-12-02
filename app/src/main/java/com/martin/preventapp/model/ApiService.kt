package com.martin.preventapp.model

import com.martin.preventapp.model.entities.Request.DeleteUserRequest
import com.martin.preventapp.model.entities.Request.LoginRequest
import com.martin.preventapp.model.entities.Request.PermissionsUpdate
import com.martin.preventapp.model.entities.Request.RegisterRequest
import com.martin.preventapp.model.entities.Request.UserToModifyRequest
import com.martin.preventapp.model.entities.Response.LoginResponse
import com.martin.preventapp.model.entities.Response.RegisterResponse
import com.martin.preventapp.model.entities.Response.TokenResponse
import com.martin.preventapp.model.entities.Response.UsersResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
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

    @POST("auth/register")
    fun registerUser(
        @Body requestBody: RegisterRequest
    ): Call<RegisterResponse>

    @GET("users/getUsers")
    fun getUsers(
        @Header("x-token") token: String,
        @Query("username") username: String
    ): Call<UsersResponse>

    @PATCH("users/updatePermissions")
    fun updatePermissionsState(
        @Header("x-token") token: String,
        @Body permissions: PermissionsUpdate
    ): Call<ResponseBody>

    @PATCH("users/updateUser")
    fun updateUser(
        @Header("x-token") token: String,
        @Body user: UserToModifyRequest
    ): Call<ResponseBody>

    @POST("users/deleteUser")
    fun deleteUser(
        @Header("x-token") token: String,
        @Body deleteUserRequest: DeleteUserRequest
    ): Call<ResponseBody>
}
