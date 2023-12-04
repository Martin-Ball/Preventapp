package com.martin.preventapp.model.entities

import com.martin.preventapp.view.entities.Product

data class ListModelEntity (
    val listName: String,
    val listProducts: List<Product>,
    val dateValidity: String
)