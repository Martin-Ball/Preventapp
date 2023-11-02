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
        fun login(userName: String)
    }

    interface Model {

    }
}