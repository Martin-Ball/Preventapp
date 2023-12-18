package com.martin.preventapp.model.entities.Response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecommendedReportResponse(
    @SerialName("recommendedReports")
    val recommendedReports: List<RecommendedReport>
)

@Serializable
data class RecommendedReport(
    @SerialName("fechaCreacion")
    val date: String,
    @SerialName("NombreCliente")
    val clientName: String,
    @SerialName("DireccionCliente")
    val addressClient: String
)