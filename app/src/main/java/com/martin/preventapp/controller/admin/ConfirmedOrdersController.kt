package com.martin.preventapp.controller.admin

import com.martin.preventapp.controller.admin.interfaces.ConfirmedOrderInterface
import com.martin.preventapp.view.adapter.OrderItemClickListener
import com.martin.preventapp.view.entities.OrderItem
import com.martin.preventapp.view.fragments.seller.orders.OrdersFragment

class ConfirmedOrdersController : ConfirmedOrderInterface.Controller, OrderItemClickListener {

    private var itemToDetail : OrderItem? = null
    private var view: ConfirmedOrderInterface.ViewOrders? = null

    companion object {
        private var ordersController: ConfirmedOrdersController? = null
        @JvmStatic
        val instance: ConfirmedOrdersController?
            get() {
                if (ordersController == null) {
                    ordersController = ConfirmedOrdersController()
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

    override fun goToMain() {
        view!!.goToMain()
    }

    override fun setView(view: ConfirmedOrderInterface.ViewOrders) {
        this.view = view
    }

    override fun onOrderItemClicked(item: OrderItem) {
        this.itemToDetail = item
        showFragmentDetail()
    }
}