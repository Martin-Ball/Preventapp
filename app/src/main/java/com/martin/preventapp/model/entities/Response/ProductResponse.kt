package com.martin.preventapp.model.entities.Response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductResponse(
    @SerialName("nombreProducto")
    val productName: String,
    @SerialName("marca")
    val brand: String,
    @SerialName("presentacion")
    val presentation: String,
    @SerialName("cantidad_unidad")
    val unit: String,
    @SerialName("cantidad")
    val amount: Int?,
    @SerialName("precio")
    val price: Double
)