package com.martin.preventapp.model.entities.Request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class RegisterRequest (
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String,
    @SerialName("type")
    val type: String,
    @SerialName("usernameAdmin")
    val usernameAdmin: String
)