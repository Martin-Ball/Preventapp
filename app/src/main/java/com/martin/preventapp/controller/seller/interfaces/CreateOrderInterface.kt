package com.martin.preventapp.controller.seller.interfaces

import androidx.fragment.app.Fragment
import com.martin.preventapp.model.entities.OrderModel
import com.martin.preventapp.view.entities.ItemAmount

interface CreateOrderInterface {
    interface View {
        //STEP CLIENT
        fun showClientActivity()
    }

    interface CompleteOrderView {
        fun showFragment(fragment: Fragment)
        fun goToMain()
    }

    interface Controller {
        //STEP PRODUCTS
        fun goToStepClient(listItems : List<ItemAmount>)

        //STEP CLIENT
        fun setViewClient(clientSelectionActivity: CompleteOrderView)
        fun setClientSelected(clientSelected : String)

        //STEP RESUME
        fun getOrder() : OrderModel
        fun sendOrder(order: OrderModel)
    }

    interface Model {
        //STEP PRODUCTS
        fun productsOrder(listItems : List<ItemAmount>)

        //STEP CLIENT
        fun setClientSelected(clientSelected:String)

        //STEP RESUME
        fun getOrder() : OrderModel
        fun sendOrder(order: OrderModel)
    }
}