package com.atakaya.weatherapp.data.remote.models


import com.google.gson.annotations.SerializedName

data class Icon(
    @SerializedName("code")
    val code: String? = null,
    @SerializedName("day")
    val day: String? = null,
    @SerializedName("icon")
    val icon: String? = null,
    @SerializedName("night")
    val night: String? = null
)
