package com.atakaya.weatherapp.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city")
data class CityDaoModel(
    @PrimaryKey(autoGenerate = true) val cityId: Int,
    val cityName: String,
)
