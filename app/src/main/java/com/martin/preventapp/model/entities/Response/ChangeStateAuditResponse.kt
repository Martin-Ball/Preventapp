package com.martin.preventapp.model.entities.Response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChangeStateAuditResponse(
    @SerialName("changeStateAudit")
    val auditList: List<AuditItem>
)

@Serializable
data class AuditItem(
    @SerialName("fechaCreacion")
    val date: String,
    @SerialName("estado")
    val state: String,
    @SerialName("FechaPedido")
    val orderDate: String,
    @SerialName("NombreCliente")
    val clientName: String,
    @SerialName("DireccionCliente")
    val addressName: String
)