package com.martin.preventapp.controller.admin.interfaces

import com.martin.preventapp.view.entities.OrderItem

interface NewOrderInterface {
    interface ViewOrders {
        fun showFragmentDetail()
    }

    interface Controller {
        fun showFragmentDetail()
        fun setItemToDetail(item: OrderItem?)
        fun getItemToDetail() : OrderItem?
    }
}