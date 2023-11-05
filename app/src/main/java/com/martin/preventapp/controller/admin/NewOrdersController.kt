package com.martin.preventapp.controller.admin

import com.martin.preventapp.controller.admin.interfaces.NewOrderInterface
import com.martin.preventapp.view.entities.OrderItem
import com.martin.preventapp.view.fragments.admin.neworders.OrdersAdminFragment

class NewOrdersController : NewOrderInterface.Controller {
    private var itemToDetail : OrderItem? = null

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

    override fun setItemToDetail(item: OrderItem?) {
        this.itemToDetail = item
    }

    override fun getItemToDetail(): OrderItem? {
        return itemToDetail
    }

}