package com.martin.preventapp.model.entities.Request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ProductRequest (
    @SerialName("nombreProducto")
    val productName: String,
    @SerialName("marca")
    val brand: String,
    @SerialName("presentacion")
    val presentation: String,
    @SerialName("cantidad_unidad")
    val unit: String,
    @SerialName("precio")
    val price: Double,
)