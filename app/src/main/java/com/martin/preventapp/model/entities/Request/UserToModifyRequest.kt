package com.martin.preventapp.model.entities.Request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class UserToModifyRequest (
    @SerialName("username")
    val username: String,
    @SerialName("newPassword")
    val newPassword: String,
    @SerialName("newGroupName")
    val newGroupName: String
)