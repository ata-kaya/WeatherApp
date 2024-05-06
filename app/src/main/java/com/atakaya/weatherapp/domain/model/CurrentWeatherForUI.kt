package com.atakaya.weatherapp.domain.model

import com.atakaya.weatherapp.data.remote.models.response.Current
import com.atakaya.weatherapp.data.remote.models.response.Location

data class CurrentWeatherForUI(
    val current: Current? = null,
    val location: Location? = null,
    val weatherIconName: String? = null
)
