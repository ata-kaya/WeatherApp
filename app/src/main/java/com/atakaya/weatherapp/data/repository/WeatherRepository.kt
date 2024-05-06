package com.atakaya.weatherapp.data.repository

import com.atakaya.weatherapp.data.remote.models.response.WeatherResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getCurrentWeather(lat: String, long: String): Flow<WeatherResponse?>
    fun getAllWeathers(): Flow<List<WeatherResponse?>>
}
