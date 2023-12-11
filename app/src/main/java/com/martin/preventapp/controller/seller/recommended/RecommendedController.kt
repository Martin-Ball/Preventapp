package com.martin.preventapp.controller.seller.recommended

import android.content.Context
import com.martin.preventapp.controller.seller.interfaces.RecommendedInterface
import com.martin.preventapp.model.entities.Response.RecommendedResponse
import com.martin.preventapp.model.seller.recommended.RecommendedModel
import com.martin.preventapp.view.entities.Client
import com.martin.preventapp.view.fragments.seller.recommended.RecommendedFragment
import com.martin.preventapp.view.fragments.seller.recommended.RecommendedProductFragment

class RecommendedController : RecommendedInterface.Controller {
    @JvmField
    var context: Context? = null
    @JvmField
    var view: RecommendedInterface.ViewActivity? = null
    private lateinit var recommendedResponse: RecommendedResponse

    private var clientSelected: String = ""

    companion object {
        private var recommendedController: RecommendedController? = null
        @JvmStatic
        val instance: RecommendedController?
            get() {
                if (recommendedController == null) {
                    recommendedController = RecommendedController()
                }
                return recommendedController
            }
    }

    override fun setContext(context: Context) {
        this.context = context
    }

    override fun setView(view: RecommendedInterface.ViewActivity) {
        this.view = view
    }

    override fun getRecommendedProducts(clientSelected: String) {
        this.clientSelected = clientSelected
        RecommendedModel.instance!!.getRecommendedProducts(clientSelected)
    }

    override fun showRecommendedProducts(list: RecommendedResponse) {
        RecommendedFragment.instance?.showRecommendedProducts()
        this.recommendedResponse = list
    }

    override fun getRecommendedResponse(): RecommendedResponse {
        return recommendedResponse
    }

    override fun getClientList() {
        RecommendedModel.instance!!.getClientList()
    }

    override fun showClientList(list: List<Client>) {
        RecommendedFragment.instance!!.showClientList(list)
    }

    override fun getClientSelected(): String {
        return clientSelected
    }

    override fun showToast(text: String) {
        RecommendedFragment.instance!!.showToast(text)
    }

    override fun goToMain() {
        view!!.goToMain()
    }
}