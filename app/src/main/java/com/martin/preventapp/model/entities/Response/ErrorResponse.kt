package com.martin.preventapp.model.entities.Response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    @SerialName("error")
    val error: ErrorDetail
)

@Serializable
data class ErrorDetail(
    @SerialName("code")
    val code: Int,
    @SerialName("message")
    val message: String
)