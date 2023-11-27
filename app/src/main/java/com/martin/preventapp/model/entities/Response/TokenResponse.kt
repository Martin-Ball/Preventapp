package com.martin.preventapp.model.entities.Response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class TokenResponse (
    @SerialName("valid")
    val valid: Boolean,
    @SerialName("token")
    val token: String
)