package com.martin.preventapp.model.entities.Request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
class CoordinatesRequest (
    val coordinates: List<List<Double>>
)