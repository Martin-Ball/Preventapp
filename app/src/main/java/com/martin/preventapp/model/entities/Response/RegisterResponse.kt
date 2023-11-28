package com.martin.preventapp.model.entities.Response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    @SerialName("user")
    val user: User,
    @SerialName("groupType")
    val groupType: GroupType,
    @SerialName("permissions")
    val permissions: List<Permission>,
    @SerialName("token")
    val token: String
)