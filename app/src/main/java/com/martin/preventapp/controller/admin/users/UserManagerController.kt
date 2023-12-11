package com.martin.preventapp.controller.admin.users

import android.content.Context
import androidx.fragment.app.Fragment
import com.martin.preventapp.controller.admin.interfaces.UserManagerInterface
import com.martin.preventapp.model.admin.managerUsers.UserManagerModel
import com.martin.preventapp.model.entities.Request.PermissionModel
import com.martin.preventapp.model.entities.UserModel
import com.martin.preventapp.model.entities.UserToModify
import com.martin.preventapp.view.fragments.admin.users.DetailUserFragment
import com.martin.preventapp.view.fragments.admin.users.UserListFragment

class UserManagerController : UserManagerInterface.Controller {

    @JvmField
    var context: Context? = null
    @JvmField
    var view: UserManagerInterface.View? = null

    private var userToModify: UserModel? = null

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

    override fun setView(view: UserManagerInterface.View) {
        this.view = view
    }

    override fun setContext(context: Context) {
        this.context = context
    }

    override fun goToMain() {
        view!!.goToMain()
    }

    override fun showFragment(fragment: Fragment) {
        view!!.showFragment(fragment)
    }

    override fun showUser(user: UserModel) {
        userToModify = user
        view!!.showFragment(DetailUserFragment.instance!!)
    }

    override fun showToast(text: String) {
        view!!.showToast(text)
    }

    override fun getUserToModify(): UserModel? {
        return userToModify
    }

    override fun getUsers() {
        UserManagerModel.instance!!.getUsers()
    }

    override fun setUsers(users: List<UserModel>) {
        UserListFragment.instance!!.setUsers(users)
    }

    override fun createUser(username: String, password: String, type: String) {
        UserManagerModel.instance!!.createUser(username, password, type)
    }

    override fun updatePermissionsState(username: String, permissions: List<PermissionModel>) {
        UserManagerModel.instance!!.updatePermissionsState(username, permissions)
    }

    override fun updateUser(userModified: UserToModify) {
        UserManagerModel.instance!!.updateUser(userModified)
    }

    override fun deleteUser(username: String) {
        UserManagerModel.instance!!.deleteUser(username)
    }
}