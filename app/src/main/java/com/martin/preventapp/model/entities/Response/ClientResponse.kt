package com.martin.preventapp.model.entities.Response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
class ClientResponse (
    @SerialName("idCliente")
    val idClient: Int,
    @SerialName("nombre")
    val name: String,
    @SerialName("direccion")
    val address: String,
    @SerialName("horarioEntrega")
    val deliveryHour: String,
    @SerialName("lat")
    val lat: String,
    @SerialName("long")
    val long: String,
)