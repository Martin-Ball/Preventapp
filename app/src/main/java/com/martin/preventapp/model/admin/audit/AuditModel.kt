package com.martin.preventapp.model.admin.audit

import android.util.Log
import com.martin.preventapp.controller.admin.audit.AuditController
import com.martin.preventapp.controller.admin.interfaces.AuditInterface
import com.martin.preventapp.controller.admin.users.UserManagerController
import com.martin.preventapp.controller.seller.createOrder.CreateOrderController
import com.martin.preventapp.model.Application
import com.martin.preventapp.model.entities.Response.ListClientResponse
import com.martin.preventapp.model.entities.Response.LoginsAuditResponse
import com.martin.preventapp.model.entities.Response.TurnoverResponse
import com.martin.preventapp.model.entities.Response.UsersResponse
import com.martin.preventapp.model.entities.UserModel
import com.martin.preventapp.view.entities.Client
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
}