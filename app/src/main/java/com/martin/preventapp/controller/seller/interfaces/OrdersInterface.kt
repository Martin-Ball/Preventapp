package com.martin.preventapp.controller.seller.interfaces

import androidx.fragment.app.Fragment
import com.martin.preventapp.view.adapter.OrderItemClickListener
import com.martin.preventapp.view.entities.NewOrder
import com.martin.preventapp.view.entities.OrderItem

interface OrdersInterface {
    interface ViewOrders {
        fun showFragmentDetail()
        fun setListener(listener: OrderItemClickListener)
    }

    interface Controller {
        fun showFragmentDetail()
        fun setItemToDetail(item: NewOrder?)
        fun getItemToDetail() : NewOrder?
    }
}