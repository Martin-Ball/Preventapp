package com.martin.preventapp.model.entities

import com.martin.preventapp.view.entities.ItemAmount

data class OrderModel(
    var client: String,
    var products: List<ItemAmount>,
    var notes: String
) {
}