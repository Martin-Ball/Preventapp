package com.martin.preventapp.model.entities.Response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductsPriceResponse(
    @SerialName("productsPrice")
    val productsPrice: List<ProductPrice>
)

@Serializable
data class ProductPrice(
    @SerialName("nombreProducto")
    val productName: String,
    @SerialName("data")
    val data: List<ProductData>
)

@Serializable
data class ProductData(
    @SerialName("nombreProducto")
    val productName: String,
    @SerialName("nombreLista")
    val listName: String,
    @SerialName("precioUnitario")
    val unitPrice: Int,
    @SerialName("fechaVigencia")
    val dateValidity: String,
    @SerialName("fechaCreacion")
    val creationDate: String
)