package com.example.machinetest.model

import com.google.gson.annotations.SerializedName



data class DataModel(
    @SerializedName("title")
    val title: String,
    @SerializedName("desc")
    val description: String)