package com.martin.preventapp.model.loginModel

import android.app.Activity
import android.view.View
import com.martin.preventapp.controller.interfaces.LoginInterfaces

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