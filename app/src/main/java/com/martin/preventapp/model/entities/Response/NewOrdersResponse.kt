package com.martin.preventapp.model.entities.Response

import com.martin.preventapp.model.entities.Request.ProductOrderRequest
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewOrdersResponse(
    @SerialName("idPedido")
    val idOrder: Int,
    @SerialName("fecha")
    val date: String,
    @SerialName("cliente")
    val client: ClientResponse,
    @SerialName("preventista")
    val sellerName: String,
    @SerialName("nota")
    val note: String,
    @SerialName("productos")
    val products: List<ProductResponse>,
)