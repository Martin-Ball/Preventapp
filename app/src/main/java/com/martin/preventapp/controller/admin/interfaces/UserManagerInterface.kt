package com.martin.preventapp.controller.admin.interfaces

import androidx.fragment.app.Fragment
import com.martin.preventapp.view.entities.User

interface UserManagerInterface {
    interface View {
        fun goToMain()
        fun showFragment(fragment: Fragment)
    }

    interface Controller {
        fun setContext(view: UserManagerInterface.View)
        fun goToMain()
        fun showFragment(fragment: Fragment)
        fun showUser(user: User)
        fun getUserToModify(): User?
    }
}