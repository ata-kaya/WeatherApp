package com.atakaya.weatherapp.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.atakaya.weatherapp.data.remote.models.response.WeatherResponse

@Entity(tableName = "weather")
data class WeatherDaoModel(
    @PrimaryKey(autoGenerate = true) val cityId: Int,
    val weather: WeatherResponse?
)
