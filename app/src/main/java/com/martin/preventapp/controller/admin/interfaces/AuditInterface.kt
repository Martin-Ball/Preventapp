package com.martin.preventapp.controller.admin.interfaces

import android.content.Context
import androidx.fragment.app.Fragment
import com.martin.preventapp.model.entities.Response.AuditItem
import com.martin.preventapp.model.entities.Response.ClientPurchasesAuditResponse
import com.martin.preventapp.model.entities.Response.ProductsPriceResponse
import com.martin.preventapp.model.entities.Response.RecommendedReport
import com.martin.preventapp.model.entities.UserModel
import com.martin.preventapp.view.entities.Client
import com.martin.preventapp.view.entities.Product
import com.martin.preventapp.view.entities.Turnover

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
        fun getLogins(username: String)
        fun showLogins(list: List<String>)
        fun getTurnover(username: String)
        fun showTurnover(list: List<Turnover>)
        fun getRecommendedReports(username: String)
        fun showRecommendedReports(list: List<RecommendedReport>)
        fun getChangeStateOrder(username: String)
        fun showChangeStateOrder(list: List<AuditItem>)
        fun getProductPrice(username: String, month: String, productName: String)
        fun showProductPrice(response: ProductsPriceResponse)
        fun getListProducts()
        fun showListProducts(list: List<Product>)

        /**CLIENTS AUDIT**/
        fun getClients()
        fun showClients(list: List<Client>)
        fun setClientSelected(client: String)
        fun getClientSelected(): String
        fun getClientPurchases(clientName: String)
        fun showClientPurchases(response: ClientPurchasesAuditResponse)
    }

    interface Model {
        fun getUsers()
        fun getClients()
        fun getLogins(username: String)
        fun getTurnover(username: String)
        fun getRecommendedReports(username: String)
        fun getChangeStateOrder(username: String)
        fun getProductPrice(username: String, month: String, productName: String)
        fun getListProducts()
        fun getClientPurchases(clientName: String)
    }
}