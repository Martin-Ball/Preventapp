package com.martin.preventapp.view.entities

data class ItemAmount(
    val name: String,
    val brand: String,
    val presentation: String,
    val quantityUnit: String,
    val price: Double,
    var quantity: Int = 0)
