package com.example.ktorclient.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PostResponse(
    val userId:Int,
    val id:Int,
    val title:String,
    val body:String
)
