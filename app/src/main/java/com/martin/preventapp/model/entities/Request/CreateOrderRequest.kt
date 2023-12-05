package com.martin.preventapp.model.entities.Request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CreateOrderRequest (
    @SerialName("cliente")
    val client: String,
    @SerialName("usuario")
    val user: String,
    @SerialName("nota")
    val note: String,
    @SerialName("productos")
    val products: List<ProductOrderRequest>,
)