package com.martin.preventapp.controller.createOrder

import com.martin.preventapp.controller.interfaces.CreateOrderInterface
import com.martin.preventapp.model.createOrder.CreateOrderModel
import com.martin.preventapp.model.entities.OrderModel
import com.martin.preventapp.view.adapter.ItemAmount
import com.martin.preventapp.view.fragments.create.ClientSelectionFragment
import com.martin.preventapp.view.fragments.create.CreateOrderFragment
import com.martin.preventapp.view.fragments.create.ResumeFragment

class CreateOrderController : CreateOrderInterface.Controller {

    @JvmField
    var contextClient: CreateOrderInterface.CompleteOrderView? = null

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
    override fun goToStepClient(listItems: List<ItemAmount>) {
        CreateOrderModel.instance?.productsOrder(listItems)
        CreateOrderFragment.instance?.showClientActivity()
    }

    //STEP CLIENT
    override fun setViewClient(clientSelectionActivity: CreateOrderInterface.CompleteOrderView) {
        this.contextClient = clientSelectionActivity
        contextClient!!.showFragment(ClientSelectionFragment.instance!!)
    }

    override fun setClientSelected(clientSelected: String) {
        CreateOrderModel.instance?.setClientSelected(clientSelected)
        contextClient!!.showFragment(ResumeFragment.instance!!)
    }

    override fun getOrder(): OrderModel {
        return CreateOrderModel.instance!!.getOrder()
    }

    override fun sendOrder(order: OrderModel) {
        CreateOrderModel.instance?.sendOrder(order)
        contextClient!!.goToMain()
    }
}