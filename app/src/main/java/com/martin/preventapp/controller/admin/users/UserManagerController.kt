package com.martin.preventapp.controller.admin.users

import androidx.fragment.app.Fragment
import com.martin.preventapp.controller.admin.interfaces.UserManagerInterface
import com.martin.preventapp.controller.admin.orders.NewOrdersController
import com.martin.preventapp.view.entities.User
import com.martin.preventapp.view.fragments.admin.users.DetailUserFragment

class UserManagerController : UserManagerInterface.Controller {

    private var view: UserManagerInterface.View? = null
    private var userToModify: User? = null

    companion object {
        private var userManagerController: UserManagerController? = null
        @JvmStatic
        val instance: UserManagerController?
            get() {
                if (userManagerController == null) {
                    userManagerController = UserManagerController()
                }
                return userManagerController
            }
    }

    override fun setContext(view: UserManagerInterface.View) {
        this.view = view
    }

    override fun goToMain() {
        view!!.goToMain()
    }

    override fun showFragment(fragment: Fragment) {
        view!!.showFragment(fragment)
    }

    override fun showUser(user: User) {
        userToModify = user
        view!!.showFragment(DetailUserFragment.instance!!)
    }

    override fun getUserToModify(): User? {
        return userToModify
    }
}