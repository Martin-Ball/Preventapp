package com.martin.preventapp.model.entities.Response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecommendedResponse(
    @SerialName("products")
    val products: List<ProductResponse>,
    @SerialName("monthlyPurchases")
    val monthlyPurchases: List<MonthlyPurchase>
)

@Serializable
data class MonthlyPurchase(
    @SerialName("mes")
    val mes: Int,
    @SerialName("total")
    val total: Int
)