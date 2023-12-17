package com.martin.preventapp.view.entities

data class ClientPurchaseAudit (
    val date: String,
    val sellerEmail: String,
    val totalAmount: String,
    val clientName: String
)