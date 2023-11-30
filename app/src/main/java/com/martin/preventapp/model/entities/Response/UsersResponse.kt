package com.martin.preventapp.model.entities.Response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UsersResponse(
    @SerialName("usuarios")
    val usersList: List<UserModelResponse>
)

@Serializable
data class UserModelResponse (
    @SerialName("idUsuario")
    val idUser: Int,
    @SerialName("nombreUsuario")
    val username: String,
    @SerialName("nombreGrupo")
    val groupName: String,
    @SerialName("estado")
    val state: Int,
    @SerialName("permisos")
    val permissions: List<PermissionModel>
)

@Serializable
data class PermissionModel(
    @SerialName("nombrePermiso")
    val permissionName: String,
    @SerialName("estado")
    var state: Int
)