package com.martin.preventapp.controller.seller.orders

import com.martin.preventapp.controller.seller.interfaces.OrdersInterface
import com.martin.preventapp.controller.seller.recommended.RecommendedController
import com.martin.preventapp.view.adapter.OrderItemClickListener
import com.martin.preventapp.view.entities.NewOrder
import com.martin.preventapp.view.entities.OrderItem
import com.martin.preventapp.view.fragments.seller.orders.OrdersFragment

class OrdersController : OrdersInterface.Controller, OrderItemClickListener {

    private var itemToDetail : NewOrder? = null

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

    override fun setItemToDetail(item: NewOrder?) {
        this.itemToDetail = item
    }

    override fun getItemToDetail(): NewOrder? {
        return itemToDetail
    }

    override fun onOrderItemClicked(item: NewOrder) {
        this.itemToDetail = item
        showFragmentDetail()
    }
}