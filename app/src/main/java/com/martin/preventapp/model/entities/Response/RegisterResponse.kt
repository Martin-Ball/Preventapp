package com.martin.preventapp.model.entities.Response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    @SerialName("newUser")
    val user: User,
    @SerialName("group")
    val groupType: GroupType,
    @SerialName("permissionsForGroup")
    val permissions: List<Permission>,
    @SerialName("token")
    val token: String
)