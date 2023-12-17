package com.martin.preventapp.model.entities.Response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ClientPurchasesAuditResponse(
    @SerialName("clientPurchases")
    val clientPurchases: List<ClientPurchase>
)

@Serializable
data class ClientPurchase(
    @SerialName("fecha")
    val date: String,
    @SerialName("preventista_email")
    val emailSeller: String,
    @SerialName("montoTotal")
    val totalAmount: Int,
    @SerialName("nombreCliente")
    val clientName: String
)