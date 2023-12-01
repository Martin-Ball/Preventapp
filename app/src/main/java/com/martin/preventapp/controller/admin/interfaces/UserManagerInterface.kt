package com.martin.preventapp.controller.admin.interfaces

import android.content.Context
import androidx.fragment.app.Fragment
import com.martin.preventapp.model.entities.Request.PermissionModel
import com.martin.preventapp.model.entities.UserModel

interface UserManagerInterface {
    interface View {
        fun goToMain()
        fun showFragment(fragment: Fragment)
        fun showToast(text: String)
    }

    interface Controller {
        fun setView(view: View)
        fun setContext(context: Context)
        fun goToMain()
        fun showFragment(fragment: Fragment)
        fun showUser(user: UserModel)
        fun showToast(text: String)
        fun getUserToModify(): UserModel?
        fun createUser(username:String, password:String, type:String)
        fun getUsers()
        fun setUsers(users: List<UserModel>)
        fun updatePermissionsState(permissions: List<PermissionModel>)
    }

    interface Model {
        fun createUser(username:String, password:String, type:String)
        fun getUsers()
        fun updatePermissionsState(permissions: List<PermissionModel>)
    }
}