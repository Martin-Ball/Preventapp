package com.martin.preventapp.model.entities.Response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RouteResponse(
    @SerializedName("features") @SerialName("features") val features: List<Feature>
)

@Serializable
data class Feature(
    @SerializedName("geometry") @SerialName("geometry") val geometry: Geometry
)

@Serializable
data class Geometry(
    @SerializedName("coordinates") @SerialName("coordinates") val coordinates: List<List<Double>>
)