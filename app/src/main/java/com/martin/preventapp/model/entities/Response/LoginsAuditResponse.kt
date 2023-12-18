package com.martin.preventapp.model.entities.Response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginsAuditResponse(
    @SerialName("logins")
    val logins: List<LoginAudit>,
)

@Serializable
data class LoginAudit(
    @SerialName("fechaInicioSesion")
    val date: String,
)