package com.martin.preventapp.controller.admin.audit

import android.content.Context
import com.martin.preventapp.controller.admin.interfaces.AuditInterface
import com.martin.preventapp.model.admin.audit.AuditModel
import com.martin.preventapp.model.entities.Response.AuditItem
import com.martin.preventapp.model.entities.Response.ClientPurchasesAuditResponse
import com.martin.preventapp.model.entities.Response.ProductPrice
import com.martin.preventapp.model.entities.Response.ProductsPriceResponse
import com.martin.preventapp.model.entities.Response.RecommendedReport
import com.martin.preventapp.model.entities.UserModel
import com.martin.preventapp.view.adapter.UsersActionInterface
import com.martin.preventapp.view.entities.Client
import com.martin.preventapp.view.entities.ClientPurchaseAudit
import com.martin.preventapp.view.entities.Product
import com.martin.preventapp.view.entities.Turnover
import com.martin.preventapp.view.fragments.admin.audit.client.AuditClientFragment
import com.martin.preventapp.view.fragments.admin.audit.client.ClientListAuditFragment
import com.martin.preventapp.view.fragments.admin.audit.client.CreationClientAuditFragment
import com.martin.preventapp.view.fragments.admin.audit.client.PurchasesClientAuditFragment
import com.martin.preventapp.view.fragments.admin.audit.price.PriceAuditFragment
import com.martin.preventapp.view.fragments.admin.audit.user.AuditUserFragment
import com.martin.preventapp.view.fragments.admin.audit.user.ChangeStateAuditFragment
import com.martin.preventapp.view.fragments.admin.audit.user.LoginAuditFragment
import com.martin.preventapp.view.fragments.admin.audit.user.RecommendedResponseFragment
import com.martin.preventapp.view.fragments.admin.audit.user.TurnoverAuditFragment
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

    override fun getTurnover(username: String) {
        AuditModel.instance!!.getTurnover(username)
    }

    override fun showTurnover(list: List<Turnover>) {
        AuditUserFragment.instance!!.showFragment(TurnoverAuditFragment.newInstance(list))
    }

    override fun getRecommendedReports(username: String) {
        AuditModel.instance!!.getRecommendedReports(username)
    }

    override fun showRecommendedReports(list: List<RecommendedReport>) {
        AuditUserFragment.instance!!.showFragment(RecommendedResponseFragment.newInstance(list))
    }

    override fun getChangeStateOrder(username: String) {
        AuditModel.instance!!.getChangeStateOrder(username)
    }

    override fun showChangeStateOrder(list: List<AuditItem>) {
        AuditUserFragment.instance!!.showFragment(ChangeStateAuditFragment.newInstance(list))
    }

    override fun getProductPrice(username: String, month: String, productName: String) {
        AuditModel.instance!!.getProductPrice(username, month, productName)
    }

    override fun showProductPrice(response: ProductsPriceResponse) {
        PriceAuditFragment.instance!!.showProductsPriceList(response)
    }

    override fun getListProducts() {
        AuditModel.instance!!.getListProducts()
    }

    override fun showListProducts(list: List<Product>) {
        PriceAuditFragment.instance!!.showListProducts(list)
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

    override fun getClientPurchases(clientName: String) {
        AuditModel.instance!!.getClientPurchases(clientName)
    }

    override fun showClientPurchases(response: ClientPurchasesAuditResponse) {
        AuditClientFragment.instance!!.showFragment(PurchasesClientAuditFragment.newInstance(response.clientPurchases.map {
            ClientPurchaseAudit(
                it.date, it.emailSeller, it.totalAmount.toString(), it.clientName
            )
        }))
    }

    override fun getCreationClient(clientName: String) {
        AuditModel.instance!!.getCreationClient(clientName)
    }

    override fun showCreationClient(date: String) {
        AuditClientFragment.instance!!.showFragment(CreationClientAuditFragment.newInstance(date))
    }
}