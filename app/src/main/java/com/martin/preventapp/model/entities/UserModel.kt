package com.martin.preventapp.model.entities

import com.martin.preventapp.model.entities.Request.PermissionModel


data class UserModel (
    val idUser: Int,
    val username: String,
    val groupName: String,
    val state: Int,
    val permissions: List<PermissionModel>
)