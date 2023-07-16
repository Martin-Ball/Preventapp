package com.martin.preventapp.controller.interfaces

import androidx.fragment.app.Fragment
import com.martin.preventapp.view.adapter.ItemAmount
import com.martin.preventapp.view.fragments.create.CompleteOrderActivity

interface CreateOrderInterface {
    interface View {
        //STEP CLIENT
        fun showClientActivity()
    }

    interface CompleteOrderView {
        fun showClientFragment(fragment: Fragment)
    }

    interface Controller {
        //STEP PRODUCTS
        fun goToStepClient(listItems : List<ItemAmount>)

        //STEP CLIENT
        fun setViewClient(clientSelectionActivity: CompleteOrderView)
        fun setClientSelected(clientSelected : String)
    }

    interface Model {
        //STEP PRODUCTS
        fun productsOrder(listItems : List<ItemAmount>)

        //STEP CLIENT
        fun setClientSelected(clientSelected:String)
    }
}