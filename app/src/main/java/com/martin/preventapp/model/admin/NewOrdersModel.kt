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
            Product("Producto 1", 1212.11), Product("Producto 12", 1212.11),
            Product("Producto 12", 1212.11),
            Product("Producto 12", 1212.11),
            Product("Producto 12", 1212.11),
            Product("Producto 12", 1212.11),
            Product("Producto 12", 1212.11),
            Product("Producto 12", 1212.11),
            Product("Producto 12", 1212.11),
            Product("Producto 12", 1212.11),
            Product("Producto 12", 1212.11)
        ), Client("Cliente 1"), "Preventista 1", "nota de pedido"),
        OrderItem("Pedido 1", listOf(Product("Producto 1", 1212.11), Product("Producto 12", 1212.11)), Client("Cliente 1"), "Preventista 1", "nota de pedido"),
        OrderItem("Pedido 2", listOf(Product("Producto 2", 1212.11), Product("Producto 13", 1212.11)), Client("Cliente 2"),"Preventista 2", "nota de pedido1"),
        OrderItem("Pedido 3", listOf(Product("Producto 3", 1212.11), Product("Producto 14", 1212.11)), Client("Cliente 3"),"Preventista 1", "nota de pedido2"),
        OrderItem("Pedido 4", listOf(Product("Producto 4", 1212.11), Product("Producto 15", 1212.11)), Client("Cliente 4"),"Preventista 2", "nota de pedido3"),
        OrderItem("Pedido 5", listOf(Product("Producto 5", 1212.11), Product("Producto 16", 1212.11)), Client("Cliente 5"),"Preventista 2", "nota de pedido4"),
        OrderItem("Pedido 6", listOf(Product("Producto 6", 1212.11), Product("Producto 17", 1212.11)), Client("Cliente 6"),"Preventista 1", "nota de pedido5"),
        OrderItem("Pedido 7", listOf(Product("Producto 7", 1212.11), Product("Producto 18", 1212.11)), Client("Cliente 7"),"Preventista 2", "nota de pedido6"),
        OrderItem("Pedido 8", listOf(Product("Producto 8", 1212.11), Product("Producto 19", 1212.11)), Client("Cliente 8"),"Preventista 1", "nota de pedido7"),
        OrderItem("Pedido 9", listOf(Product("Producto 9", 1212.11), Product("Producto 20", 1212.11)), Client("Cliente 9"), "Preventista 2","nota de pedido7"),
        OrderItem("Pedido 10", listOf(Product("Producto 10", 1212.11), Product("Producto 21", 1212.11)), Client("Cliente 10"),"Preventista 1", "nota de pedido9"),
        OrderItem("Pedido 11", listOf(Product("Producto 11", 1212.11), Product("Producto 22", 1212.11)), Client("Cliente 11"),"Preventista 2", "nota de pedido10"),

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