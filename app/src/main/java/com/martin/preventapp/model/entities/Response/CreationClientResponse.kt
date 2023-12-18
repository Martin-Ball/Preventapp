package com.martin.preventapp.model.entities.Response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreationClientResponse(
    @SerialName("creationDate")
    val date: String
)