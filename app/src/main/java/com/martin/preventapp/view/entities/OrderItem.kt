package com.martin.preventapp.view.entities

data class OrderItem(
    val title: String,
    val products: List<Product>,
    val client: Client,
    val seller: String,
    val note: String
)
