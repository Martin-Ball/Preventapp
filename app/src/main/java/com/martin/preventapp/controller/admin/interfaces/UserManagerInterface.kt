package com.martin.preventapp.controller.admin.interfaces

import androidx.fragment.app.Fragment
import com.martin.preventapp.view.entities.User

interface UserManagerInterface {
    interface View {
        fun goToMain()
        fun showFragment(fragment: Fragment)
        fun showToast(text: String)
    }

    interface Controller {
        fun setContext(view: UserManagerInterface.View)
        fun goToMain()
        fun showFragment(fragment: Fragment)
        fun showUser(user: User)
        fun showToast(text: String)
        fun getUserToModify(): User?
        fun createUser(username:String, password:String, type:String)
    }

    interface Model {
        fun createUser(username:String, password:String, type:String)
    }
}