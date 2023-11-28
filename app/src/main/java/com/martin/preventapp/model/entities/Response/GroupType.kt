package com.martin.preventapp.model.entities.Response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GroupType(
    @SerialName("idGrupo")
    val idGrupo: Int,
    @SerialName("nombreGrupo")
    val nombreGrupo: String
)