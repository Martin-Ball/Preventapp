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
    @JvmField
    var viewSplash: LoginInterfaces.ViewSplash? = null

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

    override fun setView(view: LoginInterfaces.View?) {
        this.view = view
        goToLogin()
    }

    override fun setViewSplash(view: LoginInterfaces.ViewSplash?) {
        this.viewSplash = view
    }

    override fun setContext(context: Activity?) {
        this.context = context
    }

    override fun goToMain(mainType: String) {
        if(view != null) {
            when (mainType) {
                "Preventista" -> view!!.goToActivitySeller()
                "Administrador" -> view!!.goToActivityAdmin()
                "Repartidor" -> view!!.goToActivityDelivery()
            }
        }else{
            when (mainType) {
                "Preventista" -> viewSplash!!.goToActivitySeller()
                "Administrador" -> viewSplash!!.goToActivityAdmin()
                "Repartidor" -> viewSplash!!.goToActivityDelivery()
            }
        }
    }

    override fun goToRegister() {
        view!!.goToRegister()
    }

    override fun login(username: String, password: String) {
        LoginModel.instance!!.login(username, password)
    }

    override fun validateToken() {
        LoginModel.instance!!.token()
    }

    override fun setViewReturn(isLogin: Boolean, group: String) {
        if(isLogin){
            viewSplash!!.intent()
        }else{
            goToMain(group)
        }
    }

    override fun goToLogin() {
        view!!.showSignInFragment()
    }

    override fun register(username: String, password: String) {
        LoginModel.instance!!.register(username, password)
    }

    override fun showToast(text: String) {
        view!!.showToast(text)
    }
}