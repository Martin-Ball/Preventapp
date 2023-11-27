package com.martin.preventapp.model.loginModel

import android.util.Log
import com.martin.preventapp.controller.login.LoginController
import com.martin.preventapp.controller.seller.interfaces.LoginInterfaces
import com.martin.preventapp.model.Application
import com.martin.preventapp.model.entities.Request.LoginRequest
import com.martin.preventapp.model.entities.Response.GroupType
import com.martin.preventapp.model.entities.Response.LoginResponse
import com.martin.preventapp.model.entities.Response.TokenResponse
import com.martin.preventapp.model.entities.Response.User
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class LoginModel : LoginInterfaces.Model {
    private val context = LoginController.instance!!.context!!

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
                        Application.saveTokenShared(context, loginResponse.token)
                        Application.saveUserShared(context, loginResponse.user.nombreUsuario)
                        Application.saveGroupUserShared(context, loginResponse.groupType.nombreGrupo)
                        Log.e("TOKEN: ", Application.getTokenShared(context) ?: "")
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

    override fun token() {

        val token: String
        val group: String

        try{
            token = Application.getTokenShared(context).toString()
            group = Application.getGroupUserShared(context).toString()
        }catch (e: Exception){
            LoginController.instance!!.goToLogin()
            return
        }

        val apiService = Application.getApiService()
        val call = apiService.token(token)

        call.enqueue(object : Callback<TokenResponse> {
            override fun onResponse(call: Call<TokenResponse>, response: Response<TokenResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null && loginResponse.valid) {
                        Application.saveTokenShared(context, loginResponse.token)
                        LoginController.instance!!.goToMain(group)
                    }
                } else {
                    LoginController.instance!!.goToLogin()
                }
            }

            override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
                LoginController.instance!!.goToLogin()
            }
        })
    }
}