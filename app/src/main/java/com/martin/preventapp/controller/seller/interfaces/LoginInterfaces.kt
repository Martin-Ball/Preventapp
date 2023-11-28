package com.martin.preventapp.controller.seller.interfaces

import android.app.Activity

interface LoginInterfaces {
    interface View {
        fun goToActivitySeller()
        fun goToActivityAdmin()
        fun goToActivityDelivery()
        fun showSignInFragment()
        fun showToast(text:String)
        fun goToRegister()
    }

    interface ViewSplash {
        fun intent()
        fun goToActivitySeller()
        fun goToActivityAdmin()
        fun goToActivityDelivery()
    }

    interface Controller {
        fun setView(view : View?)
        fun setViewSplash(view : ViewSplash?)
        fun setContext(context : Activity?)
        fun goToMain(mainType: String)
        fun goToLogin()
        fun goToRegister()
        fun showToast(text:String)
        fun login(username: String, password: String)
        fun register(username: String, password: String)
        fun validateToken()
        fun setViewReturn(isLogin: Boolean, group: String = "")
    }

    interface Model {
        fun login(username: String, password: String)
        fun token()
        fun register(username:String, password: String)
    }
}