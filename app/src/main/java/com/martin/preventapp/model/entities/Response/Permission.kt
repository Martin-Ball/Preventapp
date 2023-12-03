package com.martin.preventapp.model.entities.Response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Permission(
    @SerialName("nombrePermiso")
    val nombrePermiso: String,
    @SerialName("estado")
    val estado: Int
)