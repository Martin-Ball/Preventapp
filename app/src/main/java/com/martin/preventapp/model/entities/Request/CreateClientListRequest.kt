package com.martin.preventapp.model.entities.Request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
class CreateClientListRequest (
    @SerialName("usuario")
    val username: String,
    @SerialName("clientes")
    val productsList: List<ClientRequest>,
)