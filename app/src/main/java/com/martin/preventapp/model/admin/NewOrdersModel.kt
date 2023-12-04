package com.martin.preventapp.model.admin

import com.martin.preventapp.controller.admin.interfaces.NewOrderInterface
import com.martin.preventapp.view.entities.Client
import com.martin.preventapp.view.entities.OrderItem
import com.martin.preventapp.view.entities.Product

class NewOrdersModel : NewOrderInterface.Model {

    companion object {
        private var newOrderModel: NewOrdersModel? = null
        @JvmStatic
        val instance: NewOrdersModel?
            get() {
                if (newOrderModel == null) {
                    newOrderModel = NewOrdersModel()
                }
                return newOrderModel
            }
    }

    private var listItem = mutableListOf(
        OrderItem("Pedido 1", listOf(
            Product("Producto 1", "La paulina", "Unidad", "Unidad", 1212.11),
            Product("Producto 12", "La paulina", "Unidad", "Unidad", 1212.11),
            Product("Producto 12", "La paulina", "Unidad", "Unidad", 1212.11),
            Product("Producto 12", "La paulina", "Unidad", "Unidad", 1212.11),
            Product("Producto 12", "La paulina", "Unidad", "Unidad", 1212.11),
            Product("Producto 12", "La paulina", "Unidad", "Unidad", 1212.11),
        ), Client("Cliente 1"), "Preventista 1", "nota de pedido"),
        OrderItem("Pedido 1", listOf(
            Product("Producto 12", "La paulina", "Unidad", "Unidad", 1212.11),
            Product("Producto 12", "La paulina", "Unidad", "Unidad", 1212.11)),
            Client("Cliente 1"), "Preventista 1", "nota de pedido"),
        OrderItem("Pedido 1", listOf(
            Product("Producto 12", "La paulina", "Unidad", "Unidad", 1212.11),
            Product("Producto 12", "La paulina", "Unidad", "Unidad", 1212.11)),
            Client("Cliente 1"), "Preventista 1", "nota de pedido"),
        OrderItem("Pedido 1", listOf(
            Product("Producto 12", "La paulina", "Unidad", "Unidad", 1212.11),
            Product("Producto 12", "La paulina", "Unidad", "Unidad", 1212.11)),
            Client("Cliente 1"), "Preventista 1", "nota de pedido"),
        OrderItem("Pedido 1", listOf(
            Product("Producto 12", "La paulina", "Unidad", "Unidad", 1212.11),
            Product("Producto 12", "La paulina", "Unidad", "Unidad", 1212.11)),
            Client("Cliente 1"), "Preventista 1", "nota de pedido"),
        OrderItem("Pedido 1", listOf(
            Product("Producto 12", "La paulina", "Unidad", "Unidad", 1212.11),
            Product("Producto 12", "La paulina", "Unidad", "Unidad", 1212.11)),
            Client("Cliente 1"), "Preventista 1", "nota de pedido"),
        OrderItem("Pedido 1", listOf(
            Product("Producto 12", "La paulina", "Unidad", "Unidad", 1212.11),
            Product("Producto 12", "La paulina", "Unidad", "Unidad", 1212.11)),
            Client("Cliente 1"), "Preventista 1", "nota de pedido"),
        )

    override fun getNewOrders(): List<OrderItem> {
        return listItem
    }

    override fun confirmOrder(position: Int) {
        if(position != -1){
            listItem.removeAt(position)
        }
    }
}