package com.martin.preventapp.model.entities.Response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TurnoverResponse(
    @SerialName("turnover")
    val turnover: List<TurnoverItem>
)

@Serializable
data class TurnoverItem(
    @SerialName("Mes")
    val month: Int,
    @SerialName("VolumenVentas")
    val salesVolume: Double
)