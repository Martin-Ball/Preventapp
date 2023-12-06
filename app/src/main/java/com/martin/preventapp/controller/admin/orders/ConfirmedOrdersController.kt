package com.martin.preventapp.controller.admin.orders

import com.martin.preventapp.controller.admin.interfaces.ConfirmedOrderInterface
import com.martin.preventapp.model.admin.orders.NewOrdersModel
import com.martin.preventapp.view.adapter.OrderItemClickListener
import com.martin.preventapp.view.entities.NewOrder
import com.martin.preventapp.view.entities.OrderItem
import com.martin.preventapp.view.fragments.admin.neworders.ConfirmedOrdersFragment

class ConfirmedOrdersController : ConfirmedOrderInterface.Controller, OrderItemClickListener {

    private var itemToDetail : NewOrder? = null
    private var view: ConfirmedOrderInterface.ViewOrders? = null

    companion object {
        private var ordersController: ConfirmedOrdersController? = null
        @JvmStatic
        val instance: ConfirmedOrdersController?
            get() {
                if (ordersController == null) {
                    ordersController = ConfirmedOrdersController()
                }
                return ordersController
            }
    }

    override fun showFragmentDetail(item: NewOrder?) {
        NewOrdersController.instance!!.setItemToDetail(NewOrder(0, item?.state!!, item.date, item.products, item.client, item.seller, item.note), false, null)
        view!!.showFragmentDetail()
    }

    override fun setItemToDetail(item: NewOrder?) {
        this.itemToDetail = item
    }

    override fun getItemToDetail(): NewOrder? {
        return itemToDetail
    }

    override fun goToMain() {
        view!!.goToMain()
    }

    override fun setView(view: ConfirmedOrderInterface.ViewOrders) {
        this.view = view
    }

    override fun onOrderItemClicked(item: NewOrder) {
        this.itemToDetail = item
        showFragmentDetail(item)
    }

    override fun getOrdersByDate(date: String) {
        NewOrdersModel.instance!!.getOrdersByDate(date)
    }

    override fun showOrdersByDate(list: List<NewOrder>) {
        ConfirmedOrdersFragment.instance!!.showOrderDetail(list)
    }
}