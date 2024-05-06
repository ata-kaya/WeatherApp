package com.atakaya.weatherapp.data.remote.models.response


import com.google.gson.annotations.SerializedName

data class Condition(
    @SerializedName("code")
    val code: Double?,
    @SerializedName("Icon")
    val icon: String?,
    @SerializedName("text")
    val text: String?
)
