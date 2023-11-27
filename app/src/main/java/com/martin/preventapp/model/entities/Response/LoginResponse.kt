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

@Serializable
data class GroupType(
    @SerialName("idGrupo")
    val idGrupo: Int,
    @SerialName("nombreGrupo")
    val nombreGrupo: String
)

@Serializable
data class Permission(
    @SerialName("idPermiso")
    val idPermiso: Int,
    @SerialName("nombrePermiso")
    val nombrePermiso: String
)

@Serializable
data class LoginResponse(
    @SerialName("user")
    val user: User,
    @SerialName("groupType")
    val groupType: GroupType,
    @SerialName("permissions")
    val permissions: List<Permission>,
    @SerialName("token")
    val token: String
)