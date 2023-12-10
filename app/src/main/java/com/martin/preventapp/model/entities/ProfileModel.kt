package com.martin.preventapp.model.entities

import android.util.Log
import com.martin.preventapp.controller.ProfileController
import com.martin.preventapp.controller.ProfileInterface
import com.martin.preventapp.model.Application
import com.martin.preventapp.model.entities.Response.ProfileResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileModel : ProfileInterface.Model {

    companion object {
        private var profileModel: ProfileModel? = null
        @JvmStatic
        val instance: ProfileModel?
            get() {
                if (profileModel == null) {
                    profileModel = ProfileModel()
                }
                return profileModel
            }
    }

    override fun getUserInfo() {
        val apiService = Application.getApiService()

        val call = apiService.getProfile(
            Application.getTokenShared(ProfileController.instance!!.context!!) ?: "",
            Application.getUserShared(ProfileController.instance!!.context!!) ?: ""
        )

        call.enqueue(object : Callback<ProfileResponse> {
            override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {
                if (response.isSuccessful) {
                    val profileResponse = response.body()
                    if (profileResponse != null) {
                        ProfileController.instance!!.showUserInfo(profileResponse)
                    }
                } else if (response.code() == 400){
                    response.errorBody()?.string()?.let { ProfileController.instance!!.showToast(it) }
                } else{
                    Log.e("Login error: ", response.code().toString())
                }
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                Log.e("Login error: ", t.toString())
            }
        })
    }
}