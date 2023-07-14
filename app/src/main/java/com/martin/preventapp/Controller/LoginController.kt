package com.martin.preventapp.Controller

import android.app.Activity
import android.view.View
import com.martin.preventapp.Controller.Interfaces.LoginInterfaces
import com.martin.preventapp.View.Fragments.LoginFragment

class LoginController : LoginInterfaces.Controller {
    @JvmField
    var context: Activity? = null
    @JvmField
    var view: LoginInterfaces.View? = null

    companion object {
        private var loginController: LoginController? = null
        @JvmStatic
        val instance: LoginController?
            get() {
                if (loginController == null) {
                    loginController = LoginController()
                }
                return loginController
            }
    }

    override fun setView(_view: LoginInterfaces.View?) {
        this.view = _view
    }

    override fun setContext(_context: Activity?) {
        this.context = _context
    }

    override fun goMain() {
        view!!.goMain()
    }
}