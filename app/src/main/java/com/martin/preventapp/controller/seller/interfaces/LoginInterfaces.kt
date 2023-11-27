package com.martin.preventapp.controller.seller.interfaces

import android.app.Activity

interface LoginInterfaces {
    interface View {
        fun goToActivitySeller()
        fun goToActivityAdmin()
        fun goToActivityDelivery()
        fun showSignInFragment()
    }

    interface Controller {
        fun setView(_view : View?)
        fun setContext(_context : Activity?)
        fun goToMain(mainType: String)
        fun goToLogin()

        fun login(username: String, password: String)
        fun validateToken()
    }

    interface Model {
        fun login(username: String, password: String)
        fun token()
    }
}