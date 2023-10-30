package com.martin.preventapp.model.entities

import com.martin.preventapp.view.entities.ItemAmount
import com.martin.preventapp.view.entities.Product

data class OrderModel(
    var client: String,
    var products: List<Product>,
    var notes: String
) {
}