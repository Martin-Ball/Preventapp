package com.martin.preventapp.model.seller.recommended

import com.martin.preventapp.controller.seller.interfaces.RecommendedInterface
import com.martin.preventapp.controller.seller.recommended.RecommendedController

class RecommendedModel : RecommendedInterface.Model {

    private lateinit var clientSelected: String

    companion object {
        private var recommendedModel: RecommendedModel? = null
        @JvmStatic
        val instance: RecommendedModel?
            get() {
                if (recommendedModel == null) {
                    recommendedModel = RecommendedModel()
                }
                return recommendedModel
            }
    }

    override fun setClientSelected(clientSelected: String) {
        this.clientSelected = clientSelected
    }

    override fun getClientSelected(): String {
        return this.clientSelected
    }
}