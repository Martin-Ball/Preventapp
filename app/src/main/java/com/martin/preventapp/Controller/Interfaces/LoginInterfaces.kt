package com.martin.preventapp.Controller.Interfaces

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