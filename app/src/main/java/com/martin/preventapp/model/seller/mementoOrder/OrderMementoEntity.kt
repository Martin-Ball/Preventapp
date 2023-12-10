package com.martin.preventapp.model.seller.mementoOrder

import com.martin.preventapp.view.entities.Client
import com.martin.preventapp.view.entities.Product

data class OrderMementoEntity(
    val selectedProducts: List<Product>,
    val selectedClient: Client?,
    val note: String
)