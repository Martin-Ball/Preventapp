package com.martin.preventapp.controller.seller.interfaces

import android.content.Context
import androidx.fragment.app.Fragment
import com.martin.preventapp.model.entities.Response.RecommendedResponse
import com.martin.preventapp.view.entities.Client

interface RecommendedInterface {
    interface View {
        fun showRecommendedProducts()
        fun showClientList(list: List<Client>)
        fun showToast(text: String)
    }

    interface ViewActivity {
        fun showFragment(fragment: Fragment)
        fun goToMain()
    }

    interface Controller {
        fun setView(view: ViewActivity)
        fun setContext(context: Context)
        fun getRecommendedProducts(clientSelected: String)
        fun showRecommendedProducts(list: RecommendedResponse)
        fun getClientSelected(): String
        fun getRecommendedResponse(): RecommendedResponse
        fun getClientList()
        fun showClientList(list: List<Client>)
        fun showToast(text: String)
        fun goToMain()
    }

    interface Model {
        fun getClientList()
        fun getRecommendedProducts(clientSelected: String)
    }
}