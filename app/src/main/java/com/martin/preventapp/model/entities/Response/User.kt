package com.martin.preventapp.model.entities.Response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("idUsuario")
    val idUsuario: Int,
    @SerialName("nombreUsuario")
    val nombreUsuario: String,
    @SerialName("contrasena")
    val contrasena: String,
    @SerialName("estado")
    val estado: Int
)