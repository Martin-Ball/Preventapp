package com.martin.preventapp.controller.interfaces

interface RecommendedInterface {
    interface View {
        fun showRecommendedProductsActivity()
    }

    interface Controller {
        fun setClientSelected(clientSelected : String)
        fun getClientSelected() : String
    }

    interface Model {
        fun setClientSelected(clientSelected : String)
        fun getClientSelected() : String
    }
}