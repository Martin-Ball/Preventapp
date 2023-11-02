package com.martin.preventapp.controller.interfaces

import androidx.fragment.app.Fragment
import com.martin.preventapp.view.entities.OrderItem

interface OrdersInterface {
    interface ViewOrders {
        fun showFragmentDetail()
    }

    interface Controller {
        fun showFragmentDetail()
        fun setItemToDetail(item: OrderItem?)
        fun getItemToDetail() : OrderItem?
    }
}