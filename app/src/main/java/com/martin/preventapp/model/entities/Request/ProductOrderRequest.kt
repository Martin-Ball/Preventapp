package com.martin.preventapp.model.entities.Request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ProductOrderRequest (
    @SerialName("nombreProducto")
    val productName: String,
    @SerialName("cantidad")
    val amount: Int,
    @SerialName("precio")
    val price: Double,
)