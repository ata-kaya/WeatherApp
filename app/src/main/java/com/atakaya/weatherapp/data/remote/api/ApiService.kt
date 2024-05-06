package com.atakaya.weatherapp.data.remote.api

import com.atakaya.weatherapp.data.remote.models.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/current.json")
    suspend fun getCurrentWeather(@Query("q") coordinates: String): WeatherResponse?
}
