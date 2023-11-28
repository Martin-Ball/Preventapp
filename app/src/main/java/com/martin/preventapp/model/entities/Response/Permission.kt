package com.martin.preventapp.model.entities.Response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Permission(
    @SerialName("idPermiso")
    val idPermiso: Int,
    @SerialName("nombrePermiso")
    val nombrePermiso: String
)