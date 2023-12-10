package com.martin.preventapp.controller

import android.content.Context
import com.martin.preventapp.model.entities.Response.ProfileResponse

interface ProfileInterface {
    interface View {
        fun showUserInfo(info: ProfileResponse)
        fun showToast(text: String)
    }

    interface Controller {
        fun setView(view: View)
        fun setContext(context: Context)
        fun getUserInfo()
        fun showUserInfo(info: ProfileResponse)
        fun showToast(text: String)
    }

    interface Model {
        fun getUserInfo()
    }
}