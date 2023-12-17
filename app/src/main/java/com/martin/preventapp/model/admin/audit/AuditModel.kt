package com.martin.preventapp.model.admin.audit

import android.util.Log
import com.martin.preventapp.controller.admin.audit.AuditController
import com.martin.preventapp.controller.admin.interfaces.AuditInterface
import com.martin.preventapp.controller.admin.lists.ListController
import com.martin.preventapp.controller.admin.users.UserManagerController
import com.martin.preventapp.controller.seller.createOrder.CreateOrderController
import com.martin.preventapp.model.Application
import com.martin.preventapp.model.entities.Response.ChangeStateAuditResponse
import com.martin.preventapp.model.entities.Response.ClientPurchasesAuditResponse
import com.martin.preventapp.model.entities.Response.ListClientResponse
import com.martin.preventapp.model.entities.Response.ListResponse
import com.martin.preventapp.model.entities.Response.LoginsAuditResponse
import com.martin.preventapp.model.entities.Response.ProductsPriceResponse
import com.martin.preventapp.model.entities.Response.RecommendedReportResponse
import com.martin.preventapp.model.entities.Response.TurnoverResponse
import com.martin.preventapp.model.entities.Response.UsersResponse
import com.martin.preventapp.model.entities.UserModel
import com.martin.preventapp.view.entities.Client
import com.martin.preventapp.view.entities.Product
import com.martin.preventapp.view.entities.Turnover
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuditModel : AuditInterface.Model {
    companion object {
        private var auditModel: AuditModel? = null
        @JvmStatic
        val instance: AuditModel?
            get() {
                if (auditModel == null) {
                    auditModel = AuditModel()
                }
                return auditModel
            }
    }

    override fun getUsers() {
        val apiService = Application.getApiService()

        val call = apiService.getUsers(
            Application.getTokenShared(AuditController.instance!!.context!!) ?: "",
            Application.getUserShared(AuditController.instance!!.context!!) ?: ""
        )

        call.enqueue(object : Callback<UsersResponse> {
            override fun onResponse(call: Call<UsersResponse>, response: Response<UsersResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        val users = loginResponse.usersList.map {
                            UserModel(
                                it.idUser,
                                it.username,
                                it.groupName,
                                it.state,
                                it.permissions
                            )
                        }
                        AuditController.instance!!.showUsers(users)
                    }
                } else if (response.code() == 400){
                    response.errorBody()?.string()?.let { AuditController.instance!!.showToast(it) }
                } else{
                    Log.e("Login error: ", response.code().toString())
                }
            }

            override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                Log.e("Login error: ", t.toString())
            }
        })
    }

    override fun getClients() {
        val apiService = Application.getApiService()

        val call = apiService.getListClient(
            Application.getTokenShared(AuditController.instance!!.context!!) ?: "",
            Application.getUserShared(AuditController.instance!!.context!!) ?: ""
        )

        call.enqueue(object : Callback<ListClientResponse> {
            override fun onResponse(call: Call<ListClientResponse>, response: Response<ListClientResponse>) {
                if (response.isSuccessful) {
                    val responseList = response.body()
                    if (responseList != null) {
                        AuditController.instance!!.showClients(
                            responseList.listClient.map { Client(
                                it.name,
                                it.address,
                                it.deliveryHour
                            ) }
                        )
                    }
                } else if (response.code() == 400){
                    response.errorBody()?.string()?.let { AuditController.instance!!.showToast(it) }
                } else{
                    Log.e("Login error: ", response.code().toString())
                }
            }

            override fun onFailure(call: Call<ListClientResponse>, t: Throwable) {
                Log.e("Login error: ", t.toString())
            }
        })
    }

    override fun getLogins(username: String) {
        val apiService = Application.getApiService()

        val call = apiService.getLogins(
            Application.getTokenShared(AuditController.instance!!.context!!) ?: "",
            username
        )

        call.enqueue(object : Callback<LoginsAuditResponse> {
            override fun onResponse(call: Call<LoginsAuditResponse>, response: Response<LoginsAuditResponse>) {
                if (response.isSuccessful) {
                    val responseList = response.body()
                    if (responseList != null) {
                        AuditController.instance!!.showLogins(responseList.logins.map { it.date })
                    }
                } else if (response.code() == 400){
                    response.errorBody()?.string()?.let { AuditController.instance!!.showToast(it) }
                } else{
                    Log.e("Login error: ", response.code().toString())
                }
            }

            override fun onFailure(call: Call<LoginsAuditResponse>, t: Throwable) {
                Log.e("Login error: ", t.toString())
            }
        })
    }

    override fun getTurnover(username: String) {
        val apiService = Application.getApiService()

        val call = apiService.getTurnover(
            Application.getTokenShared(AuditController.instance!!.context!!) ?: "",
            username
        )

        call.enqueue(object : Callback<TurnoverResponse> {
            override fun onResponse(call: Call<TurnoverResponse>, response: Response<TurnoverResponse>) {
                if (response.isSuccessful) {
                    val responseList = response.body()
                    if (responseList != null) {
                        AuditController.instance!!.showTurnover(responseList.turnover.map {
                            Turnover(
                                it.month,
                                it.salesVolume
                            )
                        })
                    }
                } else if (response.code() == 400){
                    response.errorBody()?.string()?.let { AuditController.instance!!.showToast(it) }
                } else{
                    Log.e("Login error: ", response.code().toString())
                }
            }

            override fun onFailure(call: Call<TurnoverResponse>, t: Throwable) {
                Log.e("Login error: ", t.toString())
            }
        })
    }

    override fun getRecommendedReports(username: String) {
        val apiService = Application.getApiService()

        val call = apiService.getRecommendedReports(
            Application.getTokenShared(AuditController.instance!!.context!!) ?: "",
            username
        )

        call.enqueue(object : Callback<RecommendedReportResponse> {
            override fun onResponse(call: Call<RecommendedReportResponse>, response: Response<RecommendedReportResponse>) {
                if (response.isSuccessful) {
                    val responseList = response.body()
                    if (responseList != null) {
                        AuditController.instance!!.showRecommendedReports(responseList.recommendedReports)
                    }
                } else if (response.code() == 400){
                    response.errorBody()?.string()?.let { AuditController.instance!!.showToast(it) }
                } else{
                    Log.e("Login error: ", response.code().toString())
                }
            }

            override fun onFailure(call: Call<RecommendedReportResponse>, t: Throwable) {
                Log.e("Login error: ", t.toString())
            }
        })
    }

    override fun getChangeStateOrder(username: String) {
        val apiService = Application.getApiService()

        val call = apiService.getChangeStateOrder(
            Application.getTokenShared(AuditController.instance!!.context!!) ?: "",
            username
        )

        call.enqueue(object : Callback<ChangeStateAuditResponse> {
            override fun onResponse(call: Call<ChangeStateAuditResponse>, response: Response<ChangeStateAuditResponse>) {
                if (response.isSuccessful) {
                    val responseList = response.body()
                    if (responseList != null) {
                        AuditController.instance!!.showChangeStateOrder(responseList.auditList)
                    }
                } else if (response.code() == 400){
                    response.errorBody()?.string()?.let { AuditController.instance!!.showToast(it) }
                } else{
                    Log.e("Login error: ", response.code().toString())
                }
            }

            override fun onFailure(call: Call<ChangeStateAuditResponse>, t: Throwable) {
                Log.e("Login error: ", t.toString())
            }
        })
    }

    override fun getProductPrice(username: String, month:String, productName: String) {
        val apiService = Application.getApiService()

        val call = apiService.getProductPrice(
            Application.getTokenShared(AuditController.instance!!.context!!) ?: "",
            username,
            month,
            productName
        )

        call.enqueue(object : Callback<ProductsPriceResponse> {
            override fun onResponse(call: Call<ProductsPriceResponse>, response: Response<ProductsPriceResponse>) {
                if (response.isSuccessful) {
                    val responseList = response.body()
                    if (responseList != null) {
                        AuditController.instance!!.showProductPrice(responseList)
                    }
                } else if (response.code() == 400){
                    response.errorBody()?.string()?.let { AuditController.instance!!.showToast(it) }
                } else{
                    Log.e("Login error: ", response.code().toString())
                }
            }

            override fun onFailure(call: Call<ProductsPriceResponse>, t: Throwable) {
                Log.e("Login error: ", t.toString())
            }
        })
    }

    override fun getListProducts(){
        val apiService = Application.getApiService()

        val call = apiService.getList(
            Application.getTokenShared(AuditController.instance!!.context!!) ?: "",
            Application.getUserShared(AuditController.instance!!.context!!) ?: ""
        )

        call.enqueue(object : Callback<ListResponse> {
            override fun onResponse(call: Call<ListResponse>, response: Response<ListResponse>) {
                if (response.isSuccessful) {
                    val responseList = response.body()
                    if (responseList != null) {
                        AuditController.instance!!.showListProducts(
                            responseList.listProducts.map { Product(
                                it.productName,
                                it.brand,
                                it.presentation,
                                it.unit,
                                it.price
                            ) }
                        )
                    }else{
                        AuditController.instance!!.showToast("Error en la respuesta")
                    }
                } else if (response.code() == 400){
                    response.errorBody()?.string()?.let { AuditController.instance!!.showToast(it) }
                } else{
                    Log.e("Login error: ", response.code().toString())
                    AuditController.instance!!.showToast(response.code().toString())
                }
            }

            override fun onFailure(call: Call<ListResponse>, t: Throwable) {
                Log.e("Login error: ", t.toString())
                AuditController.instance!!.showToast(t.toString())
            }
        })
    }

    override fun getClientPurchases(clientName: String) {
        val apiService = Application.getApiService()

        val call = apiService.getClientPurchases(
            Application.getTokenShared(AuditController.instance!!.context!!) ?: "",
            clientName
        )

        call.enqueue(object : Callback<ClientPurchasesAuditResponse> {
            override fun onResponse(call: Call<ClientPurchasesAuditResponse>, response: Response<ClientPurchasesAuditResponse>) {
                if (response.isSuccessful) {
                    val responseList = response.body()
                    if (responseList != null) {
                        AuditController.instance!!.showClientPurchases(responseList)
                    }else{
                        AuditController.instance!!.showToast("Error en la respuesta")
                    }
                } else if (response.code() == 400){
                    response.errorBody()?.string()?.let { AuditController.instance!!.showToast(it) }
                } else{
                    Log.e("Login error: ", response.code().toString())
                    AuditController.instance!!.showToast(response.code().toString())
                }
            }

            override fun onFailure(call: Call<ClientPurchasesAuditResponse>, t: Throwable) {
                Log.e("Login error: ", t.toString())
                AuditController.instance!!.showToast(t.toString())
            }
        })
    }
}