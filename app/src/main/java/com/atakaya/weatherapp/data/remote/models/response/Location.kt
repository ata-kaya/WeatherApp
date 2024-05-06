package com.atakaya.weatherapp.data.remote.models.response


import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("country") val country: String? = null,
    @SerializedName("lat") val lat: Double? = null,
    @SerializedName("localtime") val localtime: String? = null,
    @SerializedName("localtime_epoch") val localtimeEpoch: Double? = null,
    @SerializedName("lon") val lon: Double? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("region") val region: String? = null,
    @SerializedName("tz_id") val tzId: String? = null,
)
