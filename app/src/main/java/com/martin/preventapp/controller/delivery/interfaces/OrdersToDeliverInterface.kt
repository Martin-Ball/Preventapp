package com.martin.preventapp.controller.delivery.interfaces

import android.content.Context
import com.martin.preventapp.view.entities.NewOrder

interface OrdersToDeliverInterface {
    interface View{
        fun showFragmentDetail()
        fun showOrdersList(list: List<NewOrder>)
        fun showToast(text: String)
    }

    interface Controller{
        fun setContext(context: Context)
        fun showFragmentDetail()
        fun getNewOrders(isAdmin: Boolean)
        fun confirmOrder()
        fun showOrdersList(list: List<NewOrder>)
        fun showToast(text: String)
    }

    interface Model {
        fun getNewOrders(isAdmin: Boolean)
        fun notDeliverOrder(id: Int)
        fun orderDelivered(id: Int)
    }
}