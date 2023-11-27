package com.martin.preventapp.model.loginModel

import android.app.Activity
import android.util.Log
import android.view.View
import com.martin.preventapp.controller.login.LoginController
import com.martin.preventapp.controller.seller.interfaces.LoginInterfaces
import com.martin.preventapp.model.Application
import com.martin.preventapp.model.entities.Request.LoginRequest
import com.martin.preventapp.model.entities.Response.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginModel : LoginInterfaces.Model {
    @JvmField
    var context: Activity? = null
    @JvmField
    var view: View? = null

    companion object {
        private var loginModel: LoginModel? = null
        @JvmStatic
        val instance: LoginModel?
            get() {
                if (loginModel == null) {
                    loginModel = LoginModel()
                }
                return loginModel
            }
    }

    override fun login(username: String, password: String) {
        val apiService = Application.getApiService()

        val call = apiService.loginUser(
            LoginRequest(username, password)
        )

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        LoginController.instance!!.goToMain(loginResponse.groupType.nombreGrupo)
                    } else {
                        Log.e("Login error: ", "Response body is null")
                    }
                } else {
                    Log.e("Login error: ", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("Login error: ", t.toString())
            }
        })
    }
}