package com.martin.preventapp.controller.admin.audit

import android.content.Context
import com.martin.preventapp.controller.admin.interfaces.AuditInterface
import com.martin.preventapp.controller.admin.interfaces.UserManagerInterface
import com.martin.preventapp.model.admin.audit.AuditModel
import com.martin.preventapp.model.entities.UserModel
import com.martin.preventapp.view.adapter.UsersActionInterface
import com.martin.preventapp.view.fragments.admin.audit.AuditFragment
import com.martin.preventapp.view.fragments.admin.audit.AuditUserFragment
import com.martin.preventapp.view.fragments.admin.audit.UserListAuditFragment
import com.martin.preventapp.view.fragments.admin.users.DetailUserFragment

class AuditController : AuditInterface.Controller, UsersActionInterface {
    @JvmField
    var context: Context? = null
    @JvmField
    var view: AuditInterface.View? = null

    private var userToModify: UserModel? = null

    companion object {
        private var auditController: AuditController? = null
        @JvmStatic
        val instance: AuditController?
            get() {
                if (auditController == null) {
                    auditController = AuditController()
                }
                return auditController
            }
    }

    override fun setView(view: AuditInterface.View) {
        this.view = view
    }

    override fun setContext(context: Context) {
        this.context = context
    }

    override fun goToMain() {
        view!!.goToMain()
    }

    override fun showToast(text: String) {
        view!!.showToast(text)
    }

    /**USER AUDIT**/
    override fun getUsers() {
        AuditModel.instance!!.getUsers()
    }

    override fun showUsers(list: List<UserModel>) {
        UserListAuditFragment.instance!!.setUsers(list)
    }

    override fun showUser(user: UserModel) {
        userToModify = user
        view!!.showFragment(AuditUserFragment.instance!!)
    }

    override fun getUserToAudit(): UserModel? {
        return userToModify
    }
}