package com.martin.preventapp.controller.admin.interfaces

import android.content.Context
import com.martin.preventapp.view.adapter.OrderItemClickListener
import com.martin.preventapp.view.entities.NewOrder
import com.martin.preventapp.view.entities.OrderItem

interface ConfirmedOrderInterface {
    interface ViewOrders {
        fun showFragment()
        fun showFragmentDetail()
        fun goToMain()

    }

    interface listener {
        fun setListener(listener: OrderItemClickListener)
    }

    interface Controller {
        fun setContext(context: Context)
        fun showFragmentDetail(item: NewOrder?)
        fun setItemToDetail(item: NewOrder?)
        fun getItemToDetail() : NewOrder?
        fun getOrdersByDate(date: String, isSeller: Boolean)
        fun showOrdersByDate(list: List<NewOrder>, isSeller: Boolean)
        fun goToMain()
        fun showToast(text:String, isSeller: Boolean)

        fun setView(view: ConfirmedOrderInterface.ViewOrders)
    }

    interface Model {
        fun getOrdersByDate(date: String, isSeller: Boolean)
    }
}