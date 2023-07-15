package com.martin.preventapp.controller.createOrder

import com.martin.preventapp.controller.interfaces.CreateOrderInterface
import com.martin.preventapp.view.fragments.create.CreateOrderFragment

class CreateOrderController : CreateOrderInterface.Controller {

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

    override fun onProductSelected(item: String) {
        //pass to another fragment
    }
}