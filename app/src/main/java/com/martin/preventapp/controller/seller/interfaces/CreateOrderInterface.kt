package com.martin.preventapp.controller.seller.interfaces

import android.content.Context
import androidx.fragment.app.Fragment
import com.martin.preventapp.model.entities.OrderModel
import com.martin.preventapp.view.entities.ItemAmount
import com.martin.preventapp.view.entities.Product

interface CreateOrderInterface {
    interface View {
        //STEP CLIENT
        fun showClientActivity()
        fun showListPrices(list: List<Product>)
        fun showToast(text: String)
    }

    interface CompleteOrderView {
        fun showFragment(fragment: Fragment)
        fun goToMain()
    }

    interface Controller {
        //STEP PRODUCTS
        fun getListProducts()
        fun showListPrices(list: List<Product>)
        fun goToStepClient(listItems : List<ItemAmount>)

        //STEP CLIENT
        fun setViewClient(clientSelectionActivity: CompleteOrderView)
        fun setContext(context: Context)
        fun setClientSelected(clientSelected : String)

        //STEP RESUME
        fun getOrder() : OrderModel
        fun sendOrder(order: OrderModel)
        fun showToast(text: String)
    }

    interface Model {
        //STEP PRODUCTS
        fun productsOrder(listItems : List<ItemAmount>)
        fun getListProducts()

        //STEP CLIENT
        fun getListClient()
        fun setClientSelected(clientSelected:String)

        //STEP RESUME
        fun getOrder() : OrderModel
        fun sendOrder(order: OrderModel)
    }
}