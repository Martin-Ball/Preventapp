package com.martin.preventapp.controller.admin.interfaces

import com.martin.preventapp.view.entities.NewOrder

interface OrderDetail {
    fun setItemToDetail(item: NewOrder?, isAdmin: Boolean, position: Int?, isNewOrder: Boolean)
    fun showFragmentDetail()
    fun getItemToDetail() : NewOrder?
    fun getIsNewOrder(): Boolean
    fun confirmOrder()
    fun cancelOrder()
    fun notDeliverOrder()
    fun orderDelivered()
    fun getIsAdmin(): Boolean

}