package com.martin.preventapp.controller.admin.orders

import com.martin.preventapp.controller.admin.interfaces.ConfirmedOrderInterface
import com.martin.preventapp.view.adapter.OrderItemClickListener
import com.martin.preventapp.view.entities.NewOrder
import com.martin.preventapp.view.entities.OrderItem

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

    override fun showFragmentDetail(item: OrderItem?) {
        NewOrdersController.instance!!.setItemToDetail(NewOrder(0, item?.date!!, item.products!!, item.client, item.seller, item.note), false, null)
        view!!.showFragmentDetail()
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
        showFragmentDetail(item)
    }
}