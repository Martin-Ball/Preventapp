package com.martin.preventapp.controller.seller.interfaces

interface RecommendedInterface {
    interface View {
        fun showRecommendedProducts()
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