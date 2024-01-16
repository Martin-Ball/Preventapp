package com.martin.preventapp.controller.delivery.interfaces

import android.content.Context
import com.martin.preventapp.model.entities.Request.CoordinatesRequest
import com.martin.preventapp.model.entities.Response.RouteResponse
import com.martin.preventapp.view.entities.NewOrder

interface OrdersToDeliverInterface {
    interface View{
        fun showFragmentDetail()
        fun showOrdersList(list: List<NewOrder>)
        fun showToast(text: String)
    }

    interface ViewMap {
        fun showRoute(route: RouteResponse)
    }

    interface Controller{
        fun setContext(context: Context)
        fun setView(view: ViewMap)
        fun showFragmentDetail()
        fun getNewOrders(isAdmin: Boolean)
        fun confirmOrder()
        fun showOrdersList(list: List<NewOrder>)
        fun showToast(text: String)

        fun setItemsToRoute(list: List<NewOrder>?)
        fun getItemsToRoute(): List<NewOrder>?
        fun getRoute(list: CoordinatesRequest)
        fun showRoute(route: RouteResponse)
    }

    interface Model {
        fun getNewOrders(isAdmin: Boolean)
        fun notDeliverOrder(id: Int)
        fun orderDelivered(id: Int)
        fun getItemsToRoute(): List<NewOrder>?
        fun setItemsToRoute(list: List<NewOrder>?)
        fun getRoute(list: CoordinatesRequest)
    }
}