package com.martin.preventapp.model.entities

import com.martin.preventapp.view.entities.Client

data class ClientListModelEntity (
    val user: String,
    val clientList: List<Client>
)
