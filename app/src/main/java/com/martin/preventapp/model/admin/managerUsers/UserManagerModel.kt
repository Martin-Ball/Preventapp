package com.martin.preventapp.model.admin.managerUsers

import android.util.Log
import com.martin.preventapp.controller.admin.interfaces.UserManagerInterface
import com.martin.preventapp.controller.admin.users.UserManagerController
import com.martin.preventapp.controller.login.LoginController
import com.martin.preventapp.model.Application
import com.martin.preventapp.model.entities.Request.RegisterRequest
import com.martin.preventapp.model.entities.Response.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserManagerModel : UserManagerInterface.Model {

    companion object {
        private var userManagerModel: UserManagerModel? = null
        @JvmStatic
        val instance: UserManagerModel?
            get() {
                if (userManagerModel == null) {
                    userManagerModel = UserManagerModel()
                }
                return userManagerModel
            }
    }

    override fun createUser(username: String, password: String, type: String) {
        val apiService = Application.getApiService()

        val call = apiService.registerUser(
            RegisterRequest(username = username, password = password, type= type, Application.getUserShared(LoginController.instance!!.context!!) ?: "")
        )

        call.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        UserManagerController.instance!!.showToast("Usuario creado ${loginResponse.user}")
                    }
                } else if (response.code() == 400){
                    response.errorBody()?.string()?.let { LoginController.instance!!.showToast(it) }
                } else{
                    Log.e("Login error: ", response.code().toString())
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Log.e("Login error: ", t.toString())
            }
        })
    }
}