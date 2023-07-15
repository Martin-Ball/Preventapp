package com.martin.preventapp.model.createOrder

import com.martin.preventapp.controller.createOrder.CreateOrderController
import com.martin.preventapp.controller.interfaces.CreateOrderInterface

class CreateOrderModel : CreateOrderInterface.Model {

    companion object {
        private var createOrderModel: CreateOrderModel? = null

        @JvmStatic
        val instance: CreateOrderModel?
            get() {
                if (createOrderModel == null) {
                    createOrderModel = CreateOrderModel()
                }
                return createOrderModel
            }
    }


}