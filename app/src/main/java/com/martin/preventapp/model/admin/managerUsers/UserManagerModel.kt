package com.martin.preventapp.model.admin.managerUsers

import android.util.Log
import com.martin.preventapp.controller.admin.interfaces.UserManagerInterface
import com.martin.preventapp.controller.admin.users.UserManagerController
import com.martin.preventapp.controller.login.LoginController
import com.martin.preventapp.model.Application
import com.martin.preventapp.model.entities.Request.DeleteUserRequest
import com.martin.preventapp.model.entities.Request.PermissionModel
import com.martin.preventapp.model.entities.Request.PermissionsUpdate
import com.martin.preventapp.model.entities.Request.RegisterRequest
import com.martin.preventapp.model.entities.Request.UserToModifyRequest
import com.martin.preventapp.model.entities.Response.RegisterResponse
import com.martin.preventapp.model.entities.Response.UsersResponse
import com.martin.preventapp.model.entities.UserModel
import com.martin.preventapp.model.entities.UserToModify
import okhttp3.ResponseBody
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

    override fun getUsers() {
        val apiService = Application.getApiService()

        val call = apiService.getUsers(
            Application.getTokenShared(UserManagerController.instance!!.context!!) ?: "",
            Application.getUserShared(UserManagerController.instance!!.context!!) ?: ""
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
                        UserManagerController.instance!!.setUsers(users)
                    }
                } else if (response.code() == 400){
                    response.errorBody()?.string()?.let { UserManagerController.instance!!.showToast(it) }
                } else{
                    Log.e("Login error: ", response.code().toString())
                }
            }

            override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                Log.e("Login error: ", t.toString())
            }
        })
    }

    override fun updatePermissionsState(username: String, permissions: List<PermissionModel>) {
        val apiService = Application.getApiService()

        val call = apiService.updatePermissionsState(
            Application.getTokenShared(UserManagerController.instance!!.context!!) ?: "",
            PermissionsUpdate(username, permissions)
        )

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        UserManagerController.instance!!.showToast("Permisos actualizados correctamente")
                    }
                } else if (response.code() == 400){
                    response.errorBody()?.string()?.let { UserManagerController.instance!!.showToast(it) }
                } else{
                    Log.e("Login error: ", response.code().toString())
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("Login error: ", t.toString())
            }
        })
    }

    override fun updateUser(userModified: UserToModify) {
        val apiService = Application.getApiService()

        val call = apiService.updateUser(
            Application.getTokenShared(UserManagerController.instance!!.context!!) ?: "",
            UserToModifyRequest(
                userModified.username,
                userModified.newPassword ?: "",
                userModified.newGroupName ?: ""
            )
        )

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        UserManagerController.instance!!.showToast("Usuario actualizados correctamente")
                    }
                } else if (response.code() == 400){
                    response.errorBody()?.string()?.let { UserManagerController.instance!!.showToast(it) }
                } else{
                    Log.e("Login error: ", response.code().toString())
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("Login error: ", t.toString())
            }
        })
    }

    override fun deleteUser(username: String) {
        val apiService = Application.getApiService()

        val call = apiService.deleteUser(
            Application.getTokenShared(UserManagerController.instance!!.context!!) ?: "",
            DeleteUserRequest(username)
        )

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        UserManagerController.instance!!.showToast("Usuario eliminado")
                    }
                } else if (response.code() == 400){
                    response.errorBody()?.string()?.let { UserManagerController.instance!!.showToast(it) }
                } else{
                    Log.e("Login error: ", response.code().toString())
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("Login error: ", t.toString())
            }
        })
    }
}