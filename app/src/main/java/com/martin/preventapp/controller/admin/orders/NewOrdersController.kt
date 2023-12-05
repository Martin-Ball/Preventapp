package com.martin.preventapp.controller.admin.orders

import android.content.Context
import com.martin.preventapp.controller.admin.interfaces.NewOrderInterface
import com.martin.preventapp.controller.seller.interfaces.CreateOrderInterface
import com.martin.preventapp.model.admin.orders.NewOrdersModel
import com.martin.preventapp.view.entities.NewOrder
import com.martin.preventapp.view.entities.OrderItem
import com.martin.preventapp.view.fragments.admin.neworders.OrdersAdminFragment

class NewOrdersController : NewOrderInterface.Controller {
    private var itemToDetail : NewOrder? = null
    private var newOrder: Boolean = false
    private var positionItem: Int = -1

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

    override fun setItemToDetail(item: NewOrder?, newOrder: Boolean, position: Int?) {
        this.newOrder = newOrder
        this.itemToDetail = item
        this.positionItem = position ?: -1
    }

    override fun getItemToDetail(): NewOrder? {
        return itemToDetail
    }
    override fun getIsNewOrder(): Boolean {
        return newOrder
    }
    override fun getNewOrders(){
        return NewOrdersModel.instance!!.getNewOrders()
    }

    override fun showOrdersList(list: List<NewOrder>) {
        OrdersAdminFragment.instance!!.showOrdersList(list)
    }

    override fun confirmOrder() {
        NewOrdersModel.instance!!.confirmOrder(positionItem)
    }

    override fun showToast(text: String) {
        OrdersAdminFragment.instance!!.showToast(text)
    }
}