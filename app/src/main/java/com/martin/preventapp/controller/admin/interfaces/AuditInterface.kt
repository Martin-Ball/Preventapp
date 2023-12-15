package com.martin.preventapp.controller.admin.interfaces

import android.content.Context
import androidx.fragment.app.Fragment
import com.martin.preventapp.model.entities.UserModel

interface AuditInterface {
    interface View {
        fun goToMain()
        fun showFragment(fragment: Fragment)
        fun showToast(text: String)
    }

    interface Controller {
        fun setView(view: AuditInterface.View)
        fun setContext(context: Context)
        fun goToMain()
        fun showToast(text: String)
        /**USER AUDIT**/
        fun getUsers()
        fun showUsers(list: List<UserModel>)
        fun getUserToAudit() : UserModel?
    }

    interface Model {
        fun getUsers()
    }
}