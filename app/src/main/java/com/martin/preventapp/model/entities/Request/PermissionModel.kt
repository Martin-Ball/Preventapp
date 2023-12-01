package com.martin.preventapp.model.entities.Request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PermissionModel(
    @SerialName("nombrePermiso")
    val permissionName: String,
    @SerialName("estado")
    var state: Int
)