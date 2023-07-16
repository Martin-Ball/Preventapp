package com.martin.preventapp.controller.interfaces

import com.martin.preventapp.view.adapter.ItemAmount
import com.martin.preventapp.view.fragments.create.ClientSelectionActivity

interface CreateOrderInterface {
    interface View {
        //STEP CLIENT
        fun showClientActivity()
    }

    interface Controller {
        //STEP PRODUCTS
        fun goToStepClient(listItems : List<ItemAmount>)

        //STEP CLIENT
        fun setViewClient(clientSelectionActivity: ClientSelectionActivity)
        fun setClientSelected(clientSelected : String)
    }

    interface Model {
        //STEP PRODUCTS
        fun productsOrder(listItems : List<ItemAmount>)

        //STEP CLIENT
        fun setClientSelected(clientSelected:String)
    }
}