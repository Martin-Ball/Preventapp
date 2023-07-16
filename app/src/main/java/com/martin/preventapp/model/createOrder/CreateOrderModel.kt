package com.martin.preventapp.model.createOrder

import com.martin.preventapp.controller.createOrder.CreateOrderController
import com.martin.preventapp.controller.interfaces.CreateOrderInterface
import com.martin.preventapp.view.adapter.ItemAmount

class CreateOrderModel : CreateOrderInterface.Model {

    private var itemList: List<ItemAmount> = listOf()

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

    override fun productsOrder(listItems: List<ItemAmount>) {
        this.itemList = listItems
    }


}