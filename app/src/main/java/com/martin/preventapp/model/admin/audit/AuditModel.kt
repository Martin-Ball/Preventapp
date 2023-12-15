package com.martin.preventapp.model.admin.audit

import android.util.Log
import com.martin.preventapp.controller.admin.audit.AuditController
import com.martin.preventapp.controller.admin.interfaces.AuditInterface
import com.martin.preventapp.controller.admin.users.UserManagerController
import com.martin.preventapp.model.Application
import com.martin.preventapp.model.entities.Response.UsersResponse
import com.martin.preventapp.model.entities.UserModel
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
}