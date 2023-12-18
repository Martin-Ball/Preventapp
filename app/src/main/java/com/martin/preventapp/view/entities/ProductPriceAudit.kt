package com.martin.preventapp.view.entities

data class ProductPriceAudit(
    val productName: String,
    val listName: String,
    val unitPrice: Int,
    val validityDate: String,
    val creationDate: String
)