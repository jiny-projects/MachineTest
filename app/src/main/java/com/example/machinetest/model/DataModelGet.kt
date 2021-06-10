package com.example.machinetest.model

import com.google.gson.annotations.SerializedName
data class DataModelGet(



    @SerializedName("id")
    var id: kotlin.Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("created_at")
    val created_at: String,
    @SerializedName("updated_at")
    val updated_at: String)
