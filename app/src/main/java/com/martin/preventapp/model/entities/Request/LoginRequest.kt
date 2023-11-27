package com.martin.preventapp.model.entities.Request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class LoginRequest (
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String
)