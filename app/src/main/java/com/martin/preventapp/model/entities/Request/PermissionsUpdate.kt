package com.martin.preventapp.model.entities.Request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class PermissionsUpdate (
    @SerialName("username")
    val username: String,
    @SerialName("permissions")
    val permissions: List<PermissionModel>
)