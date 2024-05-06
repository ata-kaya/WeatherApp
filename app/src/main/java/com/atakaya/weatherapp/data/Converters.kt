package com.atakaya.weatherapp.data

import androidx.room.TypeConverter
import com.atakaya.weatherapp.data.remote.models.response.WeatherResponse
import com.atakaya.weatherapp.domain.model.CurrentWeatherForUI
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun fromCurrentWeatherForUI(value: CurrentWeatherForUI): String {
        // Convert CurrentWeatherForUI to String
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toCurrentWeatherForUI(value: String): CurrentWeatherForUI {
        // Convert String to CurrentWeatherForUI
        return Gson().fromJson(value, CurrentWeatherForUI::class.java)
    }

    @TypeConverter
    fun toWeatherResponse(value: String): WeatherResponse {
        // Convert String to WeatherResponse
        return Gson().fromJson(value, WeatherResponse::class.java)
    }

    @TypeConverter
    fun fromWeatherResponse(value: WeatherResponse): String {
        // Convert WeatherResponse to String
        return Gson().toJson(value)
    }
}
