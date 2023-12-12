package com.martin.preventapp.controller.admin.orders

import android.content.Context
import com.martin.preventapp.controller.admin.interfaces.ConfirmedOrderInterface
import com.martin.preventapp.controller.admin.interfaces.OrderDetail
import com.martin.preventapp.model.admin.orders.NewOrdersModel
import com.martin.preventapp.view.adapter.OrderItemClickListener
import com.martin.preventapp.view.entities.NewOrder
import com.martin.preventapp.view.fragments.admin.neworders.ConfirmedOrdersFragment
import com.martin.preventapp.view.fragments.delivery.OrdersDeliveredFragment
import com.martin.preventapp.view.fragments.seller.orders.OrdersFragment

class ConfirmedOrdersController : ConfirmedOrderInterface.Controller, OrderItemClickListener {

    private var itemToDetail : NewOrder? = null
    private var view: ConfirmedOrderInterface.ViewOrders? = null
    @JvmField
    var context: Context? = null

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

    override fun setContext(context: Context) {
        this.context = context
    }

    override fun showFragmentDetail(item: NewOrder?) {
        NewOrdersController.instance!!.setItemToDetail(
            NewOrder(
                0,
                item?.state!!,
                item.date,
                item.products,
                item.client,
                item.seller,
                item.note
            ),
            true,
            null,
            false)
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

    override fun getOrdersByDate(date: String, groupType: Int) {
        NewOrdersModel.instance!!.getOrdersByDate(date, groupType)
    }

    override fun showOrdersByDate(list: List<NewOrder>, groupType: Int) {
        when(groupType){
            1 -> ConfirmedOrdersFragment.instance!!.showOrderDetail(list)
            2 -> OrdersDeliveredFragment.instance!!.showOrderDetail(list)
            3 -> OrdersFragment.instance!!.showOrderDetail(list)
        }
    }

    override fun showToast(text: String, groupType: Int) {
        when(groupType){
            1 -> ConfirmedOrdersFragment.instance!!.showToast(text)
            2 -> OrdersDeliveredFragment.instance!!.showToast(text)
            3 -> OrdersFragment.instance!!.showToast(text)
        }
    }
}