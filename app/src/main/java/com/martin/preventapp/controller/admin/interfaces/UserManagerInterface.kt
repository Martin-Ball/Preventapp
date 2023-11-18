package com.martin.preventapp.controller.admin.interfaces

import androidx.fragment.app.Fragment

interface UserManagerInterface {
    interface View {
        fun goToMain()
        fun showFragment(fragment: Fragment)
    }

    interface Controller {
        fun setContext(view: UserManagerInterface.View)
        fun goToMain()
        fun showFragment(fragment: Fragment)
    }
}