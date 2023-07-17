package com.martin.preventapp.controller.recommended

import com.martin.preventapp.controller.interfaces.RecommendedInterface
import com.martin.preventapp.model.recommended.RecommendedModel
import com.martin.preventapp.view.fragments.recommended.RecommendedFragment

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
        RecommendedFragment.instance?.showRecommendedProductsActivity()
    }

    override fun getClientSelected(): String {
        return RecommendedModel.instance!!.getClientSelected()
    }
}