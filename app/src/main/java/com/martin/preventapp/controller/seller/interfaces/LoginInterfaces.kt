package com.martin.preventapp.controller.seller.interfaces

import android.app.Activity

interface LoginInterfaces {
    interface View {
        fun goToActivitySeller()
        fun goToActivityAdmin()
        fun goToActivityDelivery()
    }

    interface Controller {
        fun setView(_view : View?)
        fun setContext(_context : Activity?)
        fun goToMain(mainType: String)

        fun login(username: String, password: String)
        fun validateToken(token: String)
    }

    interface Model {
        fun login(username: String, password: String)
    }
}