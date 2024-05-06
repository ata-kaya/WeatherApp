package com.atakaya.weatherapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.atakaya.weatherapp.data.local.models.WeatherDaoModel

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewCity(weatherDaoModel: WeatherDaoModel)

    @Query("SELECT * FROM weather")
    fun getAllWeathers(): List<WeatherDaoModel>

    @Query("SELECT * FROM weather WHERE cityId=:cityId")
    fun getWeatherById(cityId: Int): WeatherDaoModel?
}
