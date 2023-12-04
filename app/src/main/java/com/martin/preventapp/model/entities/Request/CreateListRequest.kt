package com.martin.preventapp.model.entities.Request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CreateListRequest (
    @SerialName("nombreLista")
    val listName: String,
    @SerialName("usuario")
    val username: String,
    @SerialName("productos")
    val productsList: List<ProductRequest>
)