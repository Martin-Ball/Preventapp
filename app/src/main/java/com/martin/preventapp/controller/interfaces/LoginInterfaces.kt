package com.martin.preventapp.controller.interfaces

import android.app.Activity

interface LoginInterfaces {
    interface View {
        fun goMain()
    }

    interface Controller {
        fun setView(_view : LoginInterfaces.View?)
        fun setContext(_context : Activity?)
        fun goMain()
    }

    interface Model {

    }
}