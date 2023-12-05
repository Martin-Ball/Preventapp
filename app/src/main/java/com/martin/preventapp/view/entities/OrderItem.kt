package com.martin.preventapp.view.entities

data class OrderItem(
    val products: List<Product>,
    val date: String,
    val client: Client,
    val seller: String,
    val note: String
)
