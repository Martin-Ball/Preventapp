package com.martin.preventapp.model

import com.martin.preventapp.model.entities.Request.CreateClientListRequest
import com.martin.preventapp.model.entities.Request.CreateListRequest
import com.martin.preventapp.model.entities.Request.CreateOrderRequest
import com.martin.preventapp.model.entities.Request.DeleteUserRequest
import com.martin.preventapp.model.entities.Request.LoginRequest
import com.martin.preventapp.model.entities.Request.PermissionsUpdate
import com.martin.preventapp.model.entities.Request.RegisterRequest
import com.martin.preventapp.model.entities.Request.UserToModifyRequest
import com.martin.preventapp.model.entities.Response.ChangeStateAuditResponse
import com.martin.preventapp.model.entities.Response.ClientPurchasesAuditResponse
import com.martin.preventapp.model.entities.Response.ListClientResponse
import com.martin.preventapp.model.entities.Response.NewOrdersResponse
import com.martin.preventapp.model.entities.Response.ListResponse
import com.martin.preventapp.model.entities.Response.LoginResponse
import com.martin.preventapp.model.entities.Response.LoginsAuditResponse
import com.martin.preventapp.model.entities.Response.ProductsPriceResponse
import com.martin.preventapp.model.entities.Response.ProfileResponse
import com.martin.preventapp.model.entities.Response.RecommendedReportResponse
import com.martin.preventapp.model.entities.Response.RecommendedResponse
import com.martin.preventapp.model.entities.Response.RegisterResponse
import com.martin.preventapp.model.entities.Response.TokenResponse
import com.martin.preventapp.model.entities.Response.TurnoverResponse
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

    @GET("users/getProfile")
    fun getProfile(
        @Header("x-token") token: String,
        @Query("nombreUsuario") username: String
    ): Call<ProfileResponse>

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

    @POST("pricesList/newList")
    fun createList(
        @Header("x-token") token: String,
        @Body newList: CreateListRequest
    ): Call<ResponseBody>

    @GET("pricesList/getList")
    fun getList(
        @Header("x-token") token: String,
        @Query("username") username: String
    ): Call<ListResponse>

    @POST("clientsList/newList")
    fun createListClient(
        @Header("x-token") token: String,
        @Body newList: CreateClientListRequest
    ): Call<ResponseBody>

    @GET("clientsList/getList")
    fun getListClient(
        @Header("x-token") token: String,
        @Query("username") username: String
    ): Call<ListClientResponse>

    @POST("orders/newOrder")
    fun createOrder(
        @Header("x-token") token: String,
        @Body productOrder: CreateOrderRequest
    ): Call<ResponseBody>

    @GET("orders/getNewOrders")
    fun getNewOrders(
        @Header("x-token") token: String,
        @Query("usuario") username: String,
        @Query("isAdmin") isAdmin: Boolean
    ): Call<List<NewOrdersResponse>>

    @POST("orders/sendOrderToDelivery")
    fun sendOrderToDelivery(
        @Header("x-token") token: String,
        @Query("idOrder") idOrder: Int,
        @Query("username") username: String
    ): Call<ResponseBody>

    @POST("orders/cancelOrder")
    fun cancelOrder(
        @Header("x-token") token: String,
        @Query("idOrder") idOrder: Int,
        @Query("username") username: String
    ): Call<ResponseBody>

    @GET("orders/getOrdersByDate")
    fun getOrdersByDate(
        @Header("x-token") token: String,
        @Query("usuario") username: String,
        @Query("fecha") date: String,
        @Query("groupType") groupType: Int
    ): Call<List<NewOrdersResponse>>

    @POST("orders/orderDelivered")
    fun orderDelivered(
        @Header("x-token") token: String,
        @Query("idOrder") idOrder: Int,
        @Query("username") username: String
    ): Call<ResponseBody>

    @POST("orders/notDeliverOrder")
    fun notDeliverOrder(
        @Header("x-token") token: String,
        @Query("idOrder") idOrder: Int,
        @Query("username") username: String
    ): Call<ResponseBody>

    @GET("recommended/getRecommendedProducts")
    fun getRecommendedProducts(
        @Header("x-token") token: String,
        @Query("client") client: String,
        @Query("username") username: String
    ): Call<RecommendedResponse>

    @POST("backup/createBackup")
    fun createBackup(
        @Header("x-token") token: String
    ): Call<ResponseBody>

    @POST("backup/restoreBackup")
    fun restoreBackup(
        @Header("x-token") token: String
    ): Call<ResponseBody>

    /**AUDIT**/
    @GET("audit/getLoginUser")
    fun getLogins(
        @Header("x-token") token: String,
        @Query("username") username: String
    ): Call<LoginsAuditResponse>

    @GET("audit/getTurnoverUser")
    fun getTurnover(
        @Header("x-token") token: String,
        @Query("username") username: String
    ): Call<TurnoverResponse>

    @GET("audit/getReportsRecommendedUser")
    fun getRecommendedReports(
        @Header("x-token") token: String,
        @Query("username") username: String
    ): Call<RecommendedReportResponse>

    @GET("audit/getChangeStateUser")
    fun getChangeStateOrder(
        @Header("x-token") token: String,
        @Query("username") username: String
    ): Call<ChangeStateAuditResponse>

    @GET("audit/getProductsPrice")
    fun getProductPrice(
        @Header("x-token") token: String,
        @Query("username") username: String,
        @Query("month") month: String,
        @Query("productName") productName: String,
    ): Call<ProductsPriceResponse>

    @GET("audit/getClientPurchases")
    fun getClientPurchases(
        @Header("x-token") token: String,
        @Query("clientName") clientName: String
    ): Call<ClientPurchasesAuditResponse>
}
