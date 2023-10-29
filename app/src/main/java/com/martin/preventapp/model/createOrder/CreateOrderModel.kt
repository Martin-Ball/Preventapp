package com.martin.preventapp.model.createOrder

import com.martin.preventapp.controller.interfaces.CreateOrderInterface
import com.martin.preventapp.model.entities.OrderModel
import com.martin.preventapp.view.entities.ItemAmount

class CreateOrderModel : CreateOrderInterface.Model {

    private var itemList: List<ItemAmount> = listOf()
    private var clientSelected : String = ""
    private lateinit var order : OrderModel

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

    override fun setClientSelected(clientSelected: String) {
        this.clientSelected = clientSelected
    }

    override fun getOrder(): OrderModel {
        return OrderModel(clientSelected, itemList, "")
    }

    override fun sendOrder(order: OrderModel) {
        this.order = order
    }
}