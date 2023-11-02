package com.martin.preventapp.controller.orders

import com.martin.preventapp.controller.interfaces.OrdersInterface
import com.martin.preventapp.controller.recommended.RecommendedController
import com.martin.preventapp.view.entities.OrderItem
import com.martin.preventapp.view.fragments.orders.OrdersFragment

class OrdersController : OrdersInterface.Controller {

    private var itemToDetail : OrderItem? = null

    companion object {
        private var ordersController: OrdersController? = null
        @JvmStatic
        val instance: OrdersController?
            get() {
                if (ordersController == null) {
                    ordersController = OrdersController()
                }
                return ordersController
            }
    }

    override fun showFragmentDetail() {
        OrdersFragment.instance!!.showFragmentDetail()
    }

    override fun setItemToDetail(item: OrderItem?) {
        this.itemToDetail = item
    }

    override fun getItemToDetail(): OrderItem? {
        return itemToDetail
    }
}