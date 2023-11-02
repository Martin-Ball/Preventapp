package com.martin.preventapp.controller.seller.recommended

import com.martin.preventapp.controller.seller.interfaces.RecommendedInterface
import com.martin.preventapp.model.seller.recommended.RecommendedModel
import com.martin.preventapp.view.fragments.seller.recommended.RecommendedFragment

class RecommendedController : RecommendedInterface.Controller {

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

    override fun setClientSelected(clientSelected: String) {
        RecommendedModel.instance?.setClientSelected(clientSelected)
        RecommendedFragment.instance?.showRecommendedProducts()
    }

    override fun getClientSelected(): String {
        return RecommendedModel.instance!!.getClientSelected()
    }
}