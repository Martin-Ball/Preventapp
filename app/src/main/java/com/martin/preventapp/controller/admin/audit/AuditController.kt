package com.martin.preventapp.controller.admin.audit

import android.content.Context
import com.martin.preventapp.controller.admin.interfaces.AuditInterface
import com.martin.preventapp.model.admin.audit.AuditModel
import com.martin.preventapp.model.entities.UserModel
import com.martin.preventapp.view.adapter.UsersActionInterface
import com.martin.preventapp.view.entities.Client
import com.martin.preventapp.view.fragments.admin.audit.client.AuditClientFragment
import com.martin.preventapp.view.fragments.admin.audit.client.ClientListAuditFragment
import com.martin.preventapp.view.fragments.admin.audit.user.AuditUserFragment
import com.martin.preventapp.view.fragments.admin.audit.user.LoginAuditFragment
import com.martin.preventapp.view.fragments.admin.audit.user.UserListAuditFragment

class AuditController : AuditInterface.Controller, UsersActionInterface {
    @JvmField
    var context: Context? = null
    @JvmField
    var view: AuditInterface.View? = null

    private var userToModify: UserModel? = null
    private var clientSelected: String? = null

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
        userToModify = null
        clientSelected = null
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

    override fun getLogins(username: String) {
        AuditModel.instance!!.getLogins(username)
    }

    override fun showLogins(list: List<String>) {
        AuditUserFragment.instance!!.showFragment(LoginAuditFragment.newInstance(list))
    }

    /**CLIENT AUDIT**/
    override fun getClients() {
        AuditModel.instance!!.getClients()
    }

    override fun showClients(list: List<Client>) {
        ClientListAuditFragment.instance!!.showClients(list)
    }

    override fun setClientSelected(client: String) {
        clientSelected = client
        view!!.showFragment(AuditClientFragment.instance!!)
    }

    override fun getClientSelected(): String {
        return clientSelected ?: ""
    }
}