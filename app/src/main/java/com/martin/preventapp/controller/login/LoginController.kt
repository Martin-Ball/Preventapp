package com.martin.preventapp.controller.login

import android.app.Activity
import android.content.Context
import com.martin.preventapp.controller.seller.interfaces.LoginInterfaces
import com.martin.preventapp.model.loginModel.LoginModel
import com.martin.preventapp.view.activities.seller.MainSellerActivity

class LoginController : LoginInterfaces.Controller {
    @JvmField
    var context: Context? = null
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

        validateToken()
    }

    override fun goToMain(mainType: String) {
        when (mainType) {
            "Preventista" -> view!!.goToActivitySeller()
            "Administrador" -> view!!.goToActivityAdmin()
            "Repartidor" -> view!!.goToActivityDelivery()
        }
    }

    override fun login(username: String, password: String) {
        LoginModel.instance!!.login(username, password)
    }

    override fun validateToken() {
        LoginModel.instance!!.token()
    }

    override fun goToLogin() {
        view!!.showSignInFragment()
    }
}