package com.martin.preventapp.model.entities.Response

import com.martin.preventapp.model.entities.Request.PermissionModel
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

