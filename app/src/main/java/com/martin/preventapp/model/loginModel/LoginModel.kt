package com.martin.preventapp.model.loginModel

import android.util.Log
import com.martin.preventapp.controller.login.LoginController
import com.martin.preventapp.controller.seller.interfaces.LoginInterfaces
import com.martin.preventapp.model.Application
import com.martin.preventapp.model.entities.Request.LoginRequest
import com.martin.preventapp.model.entities.Request.RegisterRequest
import com.martin.preventapp.model.entities.Response.LoginResponse
import com.martin.preventapp.model.entities.Response.RegisterResponse
import com.martin.preventapp.model.entities.Response.TokenResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.Exception

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
                    if (loginResponse != null && response.errorBody() != null) {
                        Application.saveTokenShared(context, loginResponse.token)
                        Application.saveUserShared(context, loginResponse.user.nombreUsuario)
                        Application.saveGroupUserShared(context, loginResponse.groupType.nombreGrupo)
                        Log.e("TOKEN: ", Application.getTokenShared(context) ?: "")
                        LoginController.instance!!.goToMain(loginResponse.groupType.nombreGrupo)
                    } else {
                        response.errorBody()?.string()?.let { LoginController.instance!!.showToast(it) }
                    }
                }  else if (response.code() == 400){
                    response.errorBody()?.string()?.let { LoginController.instance!!.showToast(it) }
                } else{
                    Log.e("Login error: ", response.code().toString())
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

            if(token == "null") throw Exception()
        }catch (e: Exception){
            LoginController.instance!!.setViewReturn(true)
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
                        LoginController.instance!!.setViewReturn(false, group)
                    }
                } else {
                    LoginController.instance!!.setViewReturn(true)
                }
            }

            override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
                LoginController.instance!!.setViewReturn(true)
            }
        })
    }

    override fun register(username: String, password: String) {
        val apiService = Application.getApiService()

        val call = apiService.registerUser(
            RegisterRequest(username = username, password = password, type= "Administrador")
        )

        call.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        Application.saveTokenShared(context, loginResponse.token)
                        Application.saveUserShared(context, loginResponse.user.nombreUsuario)
                        Application.saveGroupUserShared(context, loginResponse.groupType.nombreGrupo)
                        Log.e("TOKEN: ", Application.getTokenShared(context) ?: "")
                        LoginController.instance!!.goToMain(loginResponse.groupType.nombreGrupo)
                    } else {
                        LoginController.instance!!.showToast(response.body().toString())
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