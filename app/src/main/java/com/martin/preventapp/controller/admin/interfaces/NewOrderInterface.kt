package com.martin.preventapp.controller.admin.interfaces

import com.martin.preventapp.view.entities.OrderItem

interface NewOrderInterface {
    interface ViewOrders {
        fun showFragmentDetail()
    }

    interface Controller {
        fun showFragmentDetail()
        fun setItemToDetail(item: OrderItem?, newOrder: Boolean, position: Int?)
        fun getItemToDetail() : OrderItem?
        fun getIsNewOrder(): Boolean
        fun getNewOrders() : List<OrderItem>
        fun confirmOrder()
    }

    interface Model {
        fun getNewOrders() : List<OrderItem>

        fun confirmOrder(position: Int)
    }
}