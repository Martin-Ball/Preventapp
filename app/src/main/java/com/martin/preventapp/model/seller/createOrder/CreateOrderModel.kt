package com.martin.preventapp.model.seller.createOrder

import com.martin.preventapp.controller.seller.interfaces.CreateOrderInterface
import com.martin.preventapp.model.entities.OrderModel
import com.martin.preventapp.view.entities.ItemAmount
import com.martin.preventapp.view.entities.Product

class CreateOrderModel : CreateOrderInterface.Model {

    private var itemList: MutableList<Product> = mutableListOf()
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
        listItems.forEach { product ->
            this.itemList.add(Product(product.title, product.price))
        }
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