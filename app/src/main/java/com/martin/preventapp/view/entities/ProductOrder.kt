package com.martin.preventapp.view.entities

data class ProductOrder(
    val productName: String,
    val brand: String,
    val presentation: String,
    val unit: String,
    val price: Double,
    val amount: Int
)