package com.martin.preventapp.view.entities

data class NewOrder(
    val idOrder: Int,
    val products: List<Product>,
    val client: Client,
    val seller: String,
    val note: String
)