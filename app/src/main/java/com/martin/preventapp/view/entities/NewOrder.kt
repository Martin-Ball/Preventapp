package com.martin.preventapp.view.entities

data class NewOrder(
    val products: List<Product>,
    val client: Client,
    val seller: String,
    val note: String
)