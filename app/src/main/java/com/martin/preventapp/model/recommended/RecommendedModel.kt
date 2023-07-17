package com.martin.preventapp.model.recommended

import com.martin.preventapp.controller.interfaces.RecommendedInterface
import com.martin.preventapp.controller.recommended.RecommendedController

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
}