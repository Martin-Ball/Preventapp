package com.martin.preventapp.controller.admin.orders

import android.content.Context
import com.martin.preventapp.controller.admin.interfaces.NewOrderInterface
import com.martin.preventapp.controller.admin.interfaces.OrderDetail
import com.martin.preventapp.model.admin.orders.NewOrdersModel
import com.martin.preventapp.view.entities.NewOrder
import com.martin.preventapp.view.fragments.admin.neworders.OrdersAdminFragment

class NewOrdersController : NewOrderInterface.Controller, OrderDetail {
    private var itemToDetail : NewOrder? = null
    private var newOrder: Boolean = false
    private var positionItem: Int = -1
    private var isAdmin: Boolean = true

    @JvmField
    var context: Context? = null

    companion object {
        private var newOrdersController: NewOrdersController? = null
        @JvmStatic
        val instance: NewOrdersController?
            get() {
                if (newOrdersController == null) {
                    newOrdersController = NewOrdersController()
                }
                return newOrdersController
            }
    }

    override fun setContext(context: Context) {
        this.context = context
    }

    override fun showFragmentDetail() {
        OrdersAdminFragment.instance!!.showFragmentDetail()
    }

    override fun getItemToDetail(): NewOrder? {
        return itemToDetail
    }
    override fun getIsNewOrder(): Boolean {
        return newOrder
    }
    override fun getNewOrders(isAdmin: Boolean){
        return NewOrdersModel.instance!!.getNewOrders(isAdmin)
    }

    override fun getIsAdmin(): Boolean {
        return isAdmin
    }

    override fun showOrdersList(list: List<NewOrder>) {
        OrdersAdminFragment.instance!!.showOrdersList(list)
    }

    override fun confirmOrder() {
        NewOrdersModel.instance!!.confirmOrder(itemToDetail?.idOrder!!)
    }

    override fun cancelOrder() {
        NewOrdersModel.instance!!.cancelOrder(itemToDetail?.idOrder!!)
    }

    override fun notDeliverOrder() {
        //
    }

    override fun orderDelivered() {
        //
    }

    override fun showToast(text: String) {
        OrdersAdminFragment.instance!!.showToast(text)
    }

    override fun setItemToDetail(
        item: NewOrder?,
        isAdmin: Boolean,
        position: Int?,
        isNewOrder: Boolean
    ) {
        this.newOrder = isNewOrder
        this.itemToDetail = item
        this.positionItem = position ?: -1
        this.isAdmin = isAdmin
    }
}