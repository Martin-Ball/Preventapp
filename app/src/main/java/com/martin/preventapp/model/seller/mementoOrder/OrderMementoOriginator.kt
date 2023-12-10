package com.martin.preventapp.model.seller.mementoOrder

import com.martin.preventapp.view.entities.Client
import com.martin.preventapp.view.entities.Product

class OrderMementoOriginator {
    var selectedProducts: List<Product> = emptyList()
    var selectedClient: Client? = null
    var note: String = ""

    fun createMemento(): OrderMementoEntity {
        return OrderMementoEntity(selectedProducts.toList(), selectedClient, note)
    }

    fun restoreMemento(memento: OrderMementoEntity) {
        selectedProducts = memento.selectedProducts.toList()
        selectedClient = memento.selectedClient
        note = memento.note
    }
}