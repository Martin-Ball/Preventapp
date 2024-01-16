package com.martin.preventapp.controller.delivery.orders

import android.content.Context
import com.martin.preventapp.controller.admin.interfaces.OrderDetail
import com.martin.preventapp.controller.delivery.interfaces.OrdersToDeliverInterface
import com.martin.preventapp.model.entities.Request.CoordinatesRequest
import com.martin.preventapp.model.entities.Response.RouteResponse
import com.martin.preventapp.model.entities.delivery.OrdersToDeliverModel
import com.martin.preventapp.view.adapter.OrderItemClickListener
import com.martin.preventapp.view.entities.NewOrder
import com.martin.preventapp.view.fragments.delivery.OrdersDeliveryFragment

class OrdersToDeliverController: OrdersToDeliverInterface.Controller, OrderDetail {
    private var itemToDetail : NewOrder? = null
    private var newOrder: Boolean = false
    private var positionItem: Int = -1
    private var isAdmin: Boolean = false

    @JvmField
    var context: Context? = null
    @JvmField
    var view: OrdersToDeliverInterface.ViewMap? = null

    companion object {
        private var ordersToDeliver: OrdersToDeliverController? = null
        @JvmStatic
        val instance: OrdersToDeliverController?
            get() {
                if (ordersToDeliver == null) {
                    ordersToDeliver = OrdersToDeliverController()
                }
                return ordersToDeliver
            }
    }

    override fun setContext(context: Context) {
        this.context = context
    }

    override fun setView(view: OrdersToDeliverInterface.ViewMap) {
        this.view = view
    }

    override fun getNewOrders(isAdmin: Boolean) {
        OrdersToDeliverModel.instance!!.getNewOrders(isAdmin)
    }

    override fun showFragmentDetail() {
        OrdersDeliveryFragment.instance!!.showFragmentDetail()
    }

    override fun getItemToDetail(): NewOrder? {
        return itemToDetail
    }

    override fun getIsNewOrder(): Boolean {
        return newOrder
    }

    override fun getIsAdmin(): Boolean {
        return isAdmin
    }

    override fun confirmOrder() {
        //
    }

    override fun cancelOrder() {
        //
    }

    override fun notDeliverOrder() {
        OrdersToDeliverModel.instance!!.notDeliverOrder(itemToDetail?.idOrder!!)
    }

    override fun orderDelivered() {
        OrdersToDeliverModel.instance!!.orderDelivered(itemToDetail?.idOrder!!)
    }

    override fun showOrdersList(list: List<NewOrder>) {
        OrdersDeliveryFragment.instance!!.showOrdersList(list)
    }

    override fun showToast(text: String) {
        OrdersDeliveryFragment.instance!!.showToast(text)
    }

    override fun setItemsToRoute(list: List<NewOrder>?) {
        OrdersToDeliverModel.instance!!.setItemsToRoute(list)
    }

    override fun getItemsToRoute(): List<NewOrder>? {
        return OrdersToDeliverModel.instance!!.getItemsToRoute()
    }

    override fun getRoute(list: CoordinatesRequest) {
        OrdersToDeliverModel.instance!!.getRoute(list)
    }

    override fun showRoute(route: RouteResponse) {
        view!!.showRoute(route)
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