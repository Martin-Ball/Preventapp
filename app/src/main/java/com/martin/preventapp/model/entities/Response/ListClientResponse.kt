package com.martin.preventapp.model.entities.Response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListClientResponse(
    @SerialName("nombreUsuario")
    val username: String,
    @SerialName("clientes")
    val listClient: List<ClientResponse>
)