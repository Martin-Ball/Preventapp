package com.martin.preventapp.controller.admin.interfaces

import com.martin.preventapp.view.adapter.OrderItemClickListener
import com.martin.preventapp.view.entities.OrderItem

interface ConfirmedOrderInterface {
    interface ViewOrders {
        fun showFragment()
        fun goToMain()
    }

    interface listener {
        fun setListener(listener: OrderItemClickListener)
    }

    interface Controller {
        fun showFragmentDetail()
        fun setItemToDetail(item: OrderItem?)
        fun getItemToDetail() : OrderItem?
        fun goToMain()

        fun setView(view: ConfirmedOrderInterface.ViewOrders)
    }
}