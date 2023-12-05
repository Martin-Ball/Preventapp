package com.martin.preventapp.controller.seller.createOrder

import android.content.Context
import com.martin.preventapp.controller.seller.interfaces.CreateOrderInterface
import com.martin.preventapp.model.seller.createOrder.CreateOrderModel
import com.martin.preventapp.model.entities.OrderModel
import com.martin.preventapp.view.entities.Client
import com.martin.preventapp.view.entities.ItemAmount
import com.martin.preventapp.view.entities.Product
import com.martin.preventapp.view.fragments.seller.create.ClientSelectionFragment
import com.martin.preventapp.view.fragments.seller.create.CreateOrderFragment
import com.martin.preventapp.view.fragments.seller.create.ResumeFragment

class CreateOrderController : CreateOrderInterface.Controller {

    @JvmField
    var viewClient: CreateOrderInterface.CompleteOrderView? = null
    @JvmField
    var viewProducts: CreateOrderInterface.CompleteOrderView? = null
    @JvmField
    var context: Context? = null

    companion object {
        private var createOrderController: CreateOrderController? = null
        @JvmStatic
        val instance: CreateOrderController?
            get() {
                if (createOrderController == null) {
                    createOrderController = CreateOrderController()
                }
                return createOrderController
            }
    }

    //STEP PRODUCTS
    override fun setContext(context: Context) {
        this.context = context
    }

    override fun getListProducts() {
        CreateOrderModel.instance!!.getListProducts()
    }

    override fun showListPrices(list: List<Product>) {
        CreateOrderFragment.instance!!.showListPrices(list)
    }

    override fun goToStepClient(listItems: List<ItemAmount>) {
        CreateOrderModel.instance?.productsOrder(listItems)
        CreateOrderFragment.instance?.showClientActivity()
    }

    //STEP CLIENT
    override fun setViewClient(clientSelectionActivity: CreateOrderInterface.CompleteOrderView) {
        this.viewClient = clientSelectionActivity
        viewClient!!.showFragment(ClientSelectionFragment.instance!!)
        CreateOrderModel.instance!!.getListClient()
    }

    override fun showClients(list: List<Client>) {
        ClientSelectionFragment.instance!!.showClients(list)
    }

    override fun setClientSelected(clientSelected: String) {
        CreateOrderModel.instance?.setClientSelected(clientSelected)
        viewClient!!.showFragment(ResumeFragment.instance!!)
    }

    override fun getOrder(): OrderModel {
        return CreateOrderModel.instance!!.getOrder()
    }

    override fun sendOrder(order: OrderModel) {
        CreateOrderModel.instance?.sendOrder(order)
    }

    override fun showToast(text: String) {
        CreateOrderFragment.instance!!.showToast(text)
    }

    override fun goToMain() {
        viewClient!!.goToMain()
    }
}