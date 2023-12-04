package com.martin.preventapp.model.entities.Response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListResponse(
    @SerialName("idLista")
    val idList: Int,
    @SerialName("nombre")
    val listName: String,
    @SerialName("fechaVigencia")
    val validityDate: String,
    @SerialName("productos")
    val listProducts: List<ProductResponse>
)