package com.atakaya.weatherapp.data.remote.models.response

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("current")
    val current: Current?,
    @SerializedName("location")
    val location: Location?
)
