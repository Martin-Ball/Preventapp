package com.martin.preventapp.controller.admin.orders

import com.martin.preventapp.controller.admin.interfaces.NewOrderInterface
import com.martin.preventapp.model.admin.NewOrdersModel
import com.martin.preventapp.view.entities.OrderItem
import com.martin.preventapp.view.fragments.admin.neworders.OrdersAdminFragment

class NewOrdersController : NewOrderInterface.Controller {
    private var itemToDetail : OrderItem? = null
    private var newOrder: Boolean = false
    private var positionItem: Int = -1

    companion object {
        private var newOrdersController: NewOrdersController? = null
        @JvmStatic
        val instance: NewOrdersController?
            get() {
                if (newOrdersController == null) {
                    newOrdersController = NewOrdersController()
                }
                return newOrdersController
            }
    }

    override fun showFragmentDetail() {
        OrdersAdminFragment.instance!!.showFragmentDetail()
    }

    override fun setItemToDetail(item: OrderItem?, newOrder: Boolean, position: Int?) {
        this.newOrder = newOrder
        this.itemToDetail = item
        this.positionItem = position ?: -1
    }

    override fun getItemToDetail(): OrderItem? {
        return itemToDetail
    }
    override fun getIsNewOrder(): Boolean {
        return newOrder
    }
    override fun getNewOrders(): List<OrderItem> {
        return NewOrdersModel.instance!!.getNewOrders()
    }

    override fun confirmOrder() {
        NewOrdersModel.instance!!.confirmOrder(positionItem)
    }
}