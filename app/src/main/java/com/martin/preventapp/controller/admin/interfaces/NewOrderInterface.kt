package com.martin.preventapp.controller.admin.interfaces

import android.content.Context
import com.martin.preventapp.view.entities.NewOrder
import com.martin.preventapp.view.entities.OrderItem

interface NewOrderInterface {
    interface ViewOrders {
        fun showFragmentDetail()
        fun showOrdersList(list: List<NewOrder>)
        fun showToast(text: String)
    }

    interface Controller {
        fun setContext(context: Context)
        fun showFragmentDetail()
        fun setItemToDetail(item: NewOrder?, newOrder: Boolean, position: Int?)
        fun getItemToDetail() : NewOrder?
        fun getIsNewOrder(): Boolean
        fun getNewOrders()
        fun confirmOrder()
        fun cancelOrder()
        fun showOrdersList(list: List<NewOrder>)
        fun showToast(text: String)
    }

    interface Model {
        fun getNewOrders()
        fun confirmOrder(id: Int)
        fun cancelOrder(id: Int)
    }
}