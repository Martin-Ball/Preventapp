package com.martin.preventapp.controller.createOrder

import android.app.Activity
import com.martin.preventapp.controller.interfaces.CreateOrderInterface
import com.martin.preventapp.model.createOrder.CreateOrderModel
import com.martin.preventapp.view.adapter.ItemAmount
import com.martin.preventapp.view.fragments.create.ClientSelectionFragment
import com.martin.preventapp.view.fragments.create.CompleteOrderActivity
import com.martin.preventapp.view.fragments.create.CreateOrderFragment

class CreateOrderController : CreateOrderInterface.Controller {

    @JvmField
    var contextClient: CreateOrderInterface.CompleteOrderView? = null

    companion object {
        private var createOrderController: CreateOrderController? = null
        @JvmStatic
        val instance: CreateOrderController?
            get() {
                if (createOrderController == null) {
                    createOrderController = CreateOrderController()
                }
                return createOrderController
            }
    }

    //STEP PRODUCTS
    override fun goToStepClient(listItems: List<ItemAmount>) {
        CreateOrderModel.instance?.productsOrder(listItems)
        CreateOrderFragment.instance?.showClientActivity()
    }

    //STEP CLIENT
    override fun setViewClient(clientSelectionActivity: CreateOrderInterface.CompleteOrderView) {
        this.contextClient = clientSelectionActivity
        contextClient!!.showClientFragment(ClientSelectionFragment.instance!!)
    }

    override fun setClientSelected(clientSelected: String) {
        CreateOrderModel.instance?.setClientSelected(clientSelected)
    }
}