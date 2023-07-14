package com.martin.preventapp.Model

import android.app.Activity
import android.view.View
import com.martin.preventapp.Controller.Interfaces.LoginInterfaces
import com.martin.preventapp.Controller.LoginController

class LoginModel : LoginInterfaces.Model {
    @JvmField
    var context: Activity? = null
    @JvmField
    var view: View? = null

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
}