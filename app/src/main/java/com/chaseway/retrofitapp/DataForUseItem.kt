package com.chaseway.retrofitapp

import com.google.gson.annotations.SerializedName

data class DataForUseItem(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)