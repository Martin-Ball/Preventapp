package com.martin.preventapp.model.entities.Response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileResponse(
    @SerialName("idUsuario")
    val idUser: Int,
    @SerialName("nombreUsuario")
    val userName: String,
    @SerialName("idGrupo")
    val idGroup: Int,
    @SerialName("nombreGrupo")
    val groupName: String
)