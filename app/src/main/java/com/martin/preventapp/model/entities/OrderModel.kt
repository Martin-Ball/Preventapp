package com.martin.preventapp.model.entities

import com.martin.preventapp.view.entities.ItemAmount
import com.martin.preventapp.view.entities.Product
import com.martin.preventapp.view.entities.ProductOrder

data class OrderModel(
    var client: String,
    var products: List<ProductOrder>,
    var notes: String
) {
}