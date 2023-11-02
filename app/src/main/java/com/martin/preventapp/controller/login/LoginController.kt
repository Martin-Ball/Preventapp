package com.martin.preventapp.controller.login

import android.app.Activity
import com.martin.preventapp.controller.seller.interfaces.LoginInterfaces
import com.martin.preventapp.view.activities.seller.MainSellerActivity

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

    override fun login(userName: String) {
        when (userName) {
            "1" -> view!!.goToActivitySeller()
            "2" -> view!!.goToActivityAdmin()
            "3" -> view!!.goToActivityDelivery()
        }
    }
}